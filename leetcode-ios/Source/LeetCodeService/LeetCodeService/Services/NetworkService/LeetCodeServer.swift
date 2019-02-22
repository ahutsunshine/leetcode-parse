//
//  LeetCodeServer.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation
import Quicksilver
import RxSwift
import RxCocoa

// MARK: - LeetCodeServerConfig

struct APIServer {
  public private(set) static var shared: APIServer!
  
  var networkProvider: QuicksilverProvider
  var apiParamePlugin: APIRequestParamePlugin
  var baseURLString: String
  var baseHybridURLString: String
  
  public static func setup(_ server: APIServer) {
    shared = server
  }
}

// MARK: - LeetCodeAPIServer

extension JSONDecoder {
  static var LeetCodeJsonDecoder: JSONDecoder {
    let jsonDecoder = JSONDecoder()
    jsonDecoder.dateDecodingStrategy = .secondsSince1970
    return jsonDecoder
  }
}

public struct Network {
  public static let shared = Network()
}

// MARK: Network Notifications

public extension Network {
  // Notification object is URL of the request where 401 http code emit from.
  public static let httpCode401Notification = Notification.Name(rawValue: "com.liulishuo.Networking.error.401.notification")
}

public extension Network {
  private func getLeetCodeNetworkCustomError(with json: Any) -> Error? {
    if let payload = json as? [String: Any] {
      let errorMessage = payload["errorMessage"] as? String ?? "未知错误"
      return LeetCodeNetworkError.customError(message: errorMessage)
    } else {
      return nil
    }
  }
}

public extension Network {
  public func requestObservable<T: Decodable>(_ payload: LeetCodeServerAPI) -> Observable<T> {
    
    return Observable<T>.create({ observer  in
      let request = APIServer.shared.networkProvider.request(payload, completion: { (result) -> Void in
        switch result {
        case .success(let value):
          do {
            let model = try value.map(T.self, atKeyPath: nil, using: JSONDecoder.LeetCodeJsonDecoder, failsOnEmptyData: true)
            observer.onNext(model)
            observer.onCompleted()
          } catch {
            do {
              let json = try value.mapJSON()
              if let error = self.getLeetCodeNetworkCustomError(with: json) {
                observer.onError(error)
              } else {
                observer.onError(LeetCodeNetworkError.unknown)
              }
            } catch {
              observer.onError(LeetCodeNetworkError.contentDataFailed)
            }
          }
        case .failure:
          observer.onError(LeetCodeNetworkError.networkError)
        }
        
        observer.onCompleted()
      })
      
      return Disposables.create {
        request.cancel()
      }
    })
  }
  
  public func requestJSONObjectObservable(_ payload: LeetCodeServerAPI) -> Observable<Any> {
    
    return Observable<Any>.create({ observer in
      let request = APIServer.shared.networkProvider.request(payload, completion: { result in
        switch result {
        case .success(let value):
          do {
            let json = try value.mapJSON()
            if let error = self.getLeetCodeNetworkCustomError(with: json) {
              observer.onError(error)
            } else {
              observer.onNext(json)
              observer.onCompleted()
            }
          } catch {
            observer.onError(LeetCodeNetworkError.contentDataFailed)
          }
        case .failure:
          observer.onError(LeetCodeNetworkError.networkError)
        }
      })
      
      return Disposables.create {
        request.cancel()
      }
    })
  }
  
  public func requestWithoutResponseBodyObservable(_ payload: LeetCodeServerAPI) -> Observable<Void> {
    return Observable<Void>.create({ observer in
      let request = APIServer.shared.networkProvider.request(payload) { result in
        switch result {
        case .success(let value):
          if let json = try? value.mapJSON(),
            let error = self.getLeetCodeNetworkCustomError(with: json) {
            observer.onError(error)
          } else if value.statusCode >= 400 {
            observer.onError(LeetCodeNetworkError.networkError)
          } else {
            observer.onCompleted()
          }
        case .failure:
          observer.onError(LeetCodeNetworkError.networkError)
        }
      }
      
      return Disposables.create {
        request.cancel()
      }
    })
  }
}

// MARK: - LeetCodeServerAPI

public protocol LeetCodeServerAPI: DataTargetType, AccessTokenAuthorizable { }

public extension LeetCodeServerAPI {
  var baseURL: URL {
    return URL(string: APIServer.shared.baseURLString)!
  }
  
  var authorizationType: AuthorizationType {
    return .bearer
  }
  
  var sampleResponse: SampleResponseClosure? {
    return nil
  }
  
  var validation: ValidationType {
    return ValidationType.customCodes(Array(200..<500))
  }
}

public protocol LeetCodeServerMockAPI: LeetCodeServerAPI {
  var mockDataAvailable: Bool { get }
  var mockResponse: SampleResponseClosure? { get }
}

// MARK: -LeetCodeUploadAPI

public protocol LeetCodeUploadAPI: UploadTargetType, AccessTokenAuthorizable {
  var path: String? { get }
}

public extension LeetCodeUploadAPI {
  
  var uploadURL: URL {
    return Network.getFullUrl(withBaseUrlString: APIServer.shared.baseURLString, path: path)
  }
  
  public var authorizationType: Quicksilver.AuthorizationType {
    return .bearer
  }
  
  var validation: ValidationType {
    return ValidationType.customCodes(Array(200..<500))
  }
}


// MARK: - APIRequestParamePlugin

public class APIRequestParamePlugin: PluginType {
  
  public init() {}
  
  public var extraHTTPHeaders: [String: String] = [:]
  
  public func prepare(_ request: URLRequest, target: TargetType) -> URLRequest {
    var finalRequest = request
    finalRequest.setValue("zh;q=1", forHTTPHeaderField: "Accept-Language")
    for (key, value) in extraHTTPHeaders {
      finalRequest.setValue(value, forHTTPHeaderField: key)
    }
    handleIfHost(&finalRequest)
    return finalRequest
  }
  
  public func didReceive(_ result: Quicksilver.Result<Response, QuicksilverError>, target: TargetType) {
    if let response = result.value, response.statusCode == 401 {
      OperationQueue.main.addOperation {
        NotificationCenter.default.post(name: Network.httpCode401Notification, object: response.request?.url)
      }
    }
  }
  
}

extension APIRequestParamePlugin {
  func isHost(host: String?) -> Bool {
    return true
  }
  
  func handleIfHost( _ request: inout URLRequest) {
    guard let url = request.url else { return }
    let headerHost = request.allHTTPHeaderFields?["Host"]
    guard isHost(host: headerHost) || isHost(host: url.host) else { return }

    var components = URLComponents(url: url, resolvingAgainstBaseURL: true)
    if let items = components?.queryItems {
      components?.queryItems = items
    }
    if let urlString = components?.string {
      request.url = URL(string: urlString)
    }
    
    if let token = User.currentUserToken {
      request.setValue("Bearer " + token, forHTTPHeaderField: "Authorization")
    }
  }
}

// MARK: -getFullUrl
public extension Network {
  public static func getFullUrl(withBaseUrlString baseUrlString: String, path: String?) -> URL {
    guard let baseUrl = URL(string: baseUrlString) else {
      return URL(string: APIServer.shared.baseURLString)!
    }
    
    guard var path = path, var components = URLComponents(string: baseUrlString) else {
      return baseUrl
    }
    
    func removeLastBackSlash(_ forString: String) -> String {
      var str = forString
      if str.hasSuffix("/") {
        str.removeLast()
      } else {
        return str
      }
      return removeLastBackSlash(str)
    }
    
    func removeFirstBackSlash(_ forString: String) -> String {
      var str = forString
      if str.hasPrefix("/") {
        str.removeFirst()
      } else {
        return str
      }
      return removeFirstBackSlash(str)
    }
    
    components.path = removeLastBackSlash(components.path)
    path = removeFirstBackSlash(path)
    if !path.isEmpty {
      path.insert("/", at: .init(encodedOffset: 0))
      components.path.append(path)
    }
    guard let url = components.url else {
      return baseUrl
    }
    return url
  }
}

