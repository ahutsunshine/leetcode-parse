//
//  NetworkServiceConfiguration.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Rainbow
import Quicksilver

public class NetworkServiceConfiguration: AppConfigurationable {
  public init() {
    NotificationCenter.default.addObserver(self, selector: #selector(userIsNotAuthorized), name: Network.httpCode401Notification, object: nil)
  }

  private func environmentSetup() {
    let environment = AppConfiguration.shared.environment
    let apiParamePlugin = APIRequestParamePlugin()
    let loggerPlugin: NetworkLoggerPlugin = NetworkLoggerPlugin(cURL: true, output: { (log) in
      #if DEBUG || QA_BUILD
//      Logger.debug(log, "serverapi")
      #else
      let thresold = 5000
      if log.count > thresold {
//        Logger.debug(log.prefix(thresold) + " ...\nFurther log reduced for size", "serverapi")
      } else {
//        Logger.debug(log, "serverapi")
      }
      #endif
    }, requestDataFormatter: nil, responseDataFormatter: nil)
    
    let configuration = QuicksilverURLSessionConfiguration(useHTTPDNS: environment.usesHttpDNS, httpsCertificateLocalVerify: environment.httpsCertificateLocalVerify, certificatesBundle: environment.certificatesBundle, stub: environment.stubBehavior)
    configuration.urlSessionConfiguration.timeoutIntervalForRequest = 10
    let quicksilverProvider = QuicksilverProvider(configuration: configuration, plugins: [apiParamePlugin, loggerPlugin], callbackQueue: DispatchQueue.main)
    APIServer.setup(APIServer(networkProvider: quicksilverProvider,
                              apiParamePlugin: apiParamePlugin,
                              baseURLString: environment.baseURLString,
                              baseHybridURLString: environment.baseHybridURLString))
  }
  
  public var priority: Int {
    return 999
  }
  
  public func setup(user: User?) {
    environmentSetup()
    APIServer.shared.apiParamePlugin.extraHTTPHeaders = generateExtraHTTPHeaders(token: User.currentUserToken)
  }
  
  public func userWillLogin(user: User) {
    APIServer.shared.apiParamePlugin.extraHTTPHeaders = generateExtraHTTPHeaders(token: User.currentUserToken)
  }
  
  public func userDidLogout(user: User) {
    APIServer.shared.apiParamePlugin.extraHTTPHeaders = generateExtraHTTPHeaders(token: nil)
  }
  
  public func generateExtraHTTPHeaders(token: String?) -> [String: String] {
    var defaultParameters = [String: String]()
    defaultParameters["token"] = token
    if AppConfiguration.shared.environment == .feature {
      // Dev environment
    }
    return defaultParameters
  }
  
  @objc private func userIsNotAuthorized() {
    LoginManager.logoutCleanup()
    let logoutAlert = UIAlertController(title: "因为用户登录信息过期，需要重新登录", message: nil, preferredStyle: .alert)
    logoutAlert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
    UIApplication.shared.windows.first?.rootViewController?.present(logoutAlert, animated: true, completion: nil)
  }
}

extension ModuleContext.Environment {
  
  public var baseURLString: String {
    switch self {
    case .test:
      return "http://localhost:8080/"
    case .feature, .stage:
      return AppConstants.LeetCodeServer.stagingBaseURLString
    case .prod:
      #if INTERNAL_ENTERPRISE
      return AppConstants.LeetCodeServer.stagingBaseURLString
      #else
      return AppConstants.LeetCodeServer.productionBaseURLString
      #endif
    }
  }
  
  public var baseHybridURLString: String {
    #if DEBUG || QA_BUILD
    return "https://sprout.llsstaging.com"
    #else
    return "https://sprout.liulishuo.com"
    #endif
  }
  
  public var usesHttpDNS: Bool {
    switch self {
    case .prod:
      return true
    default:
      return false
    }
  }
  
  public var httpsCertificateLocalVerify: Bool {
    #if DEBUG || QA_BUILD
    return false
    #else
    return true
    #endif
  }
  
  public var certificatesBundle: Bundle? {
    #if DEBUG || QA_BUILD
    return nil
    #else
    return AppConstants.LeetCodeServer.certificateBundle
    #endif
  }
  
  public var appID: String {
    return AppConstants.LeetCodeServer.appId
  }
  
  public var stubBehavior: StubBehavior {
    return .never
  }
  
  var qiniuEnvironmentPrefix: String {
    switch self {
    case .feature, .stage:
      return "staging"
    case .test, .prod:
      return "production"
    }
  }
}

