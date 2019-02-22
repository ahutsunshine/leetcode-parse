//
//  User.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import UIKit
import KeychainSwift
import CocoaLumberjackSwift

public struct User: Codable {
  public let id: String // userID
  public var token: String? // 只能从网络请求获取的 User 有，本地存储的没有（安全原因）
  
  public static let didUpdateNotification = Notification.Name("com.liulishuo.userDidUpdateNotification")
  #if DEBUG || QA_BUILD
  // Need read and update raw user data in FLEX
  static let userArchiveKey = "com.ahut.userArchiveKey"
  #else
  fileprivate static let userArchiveKey = "com.ahut.userArchiveKey"
  #endif
  fileprivate var userTokenKey: String {
    return "com.ahut.userTokenKey.\(id)"
  }
  
  public func save() throws {
    if let token = token {
      KeychainSwift().set(token, forKey: userTokenKey)
    }
    var saveUser = self
    saveUser.token = nil
    let userData = try JSONEncoder().encode(saveUser)
    UserDefaults.standard.set(userData, forKey: User.userArchiveKey)
    DDLogDebug("User did save \(saveUser)")
    NotificationCenter.default.post(name: User.didUpdateNotification, object: saveUser)
  }
  
  public static func update(user: User?) {
    try? user?.save()
  }
  
  public static var current: User? {
    if let userData = UserDefaults.standard.data(forKey: userArchiveKey),
      let user = try? JSONDecoder().decode(User.self, from: userData) {
      return user
    }
    return nil
  }
  
  public static var currentUserToken: String? {
    return (current?.userTokenKey).flatMap(KeychainSwift().get)
  }
}

public class LoginManager {
  public enum LoginError: Error  {
    case invalidUserToken
    
    public var localizedDescription: String {
      switch self {
      case .invalidUserToken:
        return "Token of tring to login user is invalid."
      }
    }
  }
  
  static let shared = LoginManager()
  
  public static func login(user: User) throws {
    guard !(user.token?.isEmpty ?? true) else {
      throw LoginError.invalidUserToken
    }
    try user.save()
    AppConfiguration.shared.userWillLogin(user: user)
  }
  
  public static func finishUserGuide(user: User) {
    do {
      try user.save()
      AppConfiguration.shared.userDidLogin(user: user)
    } catch {
      DDLogError("save user error when finish user guide\n\(user)")
      logoutCleanup()
    }
  }
  
  public static func logout(completion: ((Error?) -> Void)?) {
    if let user = User.current {
      AppConfiguration.shared.userWillLogout(user: user)
    }
  }
  
  public static func logoutCleanup() {
    if let user = User.current {
      KeychainSwift().delete(user.userTokenKey)
      UserDefaults.standard.removeObject(forKey: User.userArchiveKey)
      DDLogDebug("User did logout \(user)")
      AppConfiguration.shared.userDidLogout(user: user)
    }
  }
}

// MARK: - UserLifecyle

protocol UserLifecyleService: class {
  
  /// 用户登陆操作成功，通过 Backend 拿到了 User Raw Data 时会调用
  ///
  /// - Parameter user: 当前用户信息
  func userWillLogin(user: User)
  
  /// 用户完成登陆，用户信息已存入数据库，新手引导也已经完成时会调用
  ///
  /// - Parameter user: 当前用户信息
  func userDidLogin(user: User)
  
  /// 用户将要登出，发送登出网络请求前调用
  ///
  /// - Parameter user: 当前用户信息
  func userWillLogout(user: User)
  
  /// 用户登出完成，数据已经被清理完时调用
  ///
  /// - Parameter user: 当前用户信息
  func userDidLogout(user: User)
}
