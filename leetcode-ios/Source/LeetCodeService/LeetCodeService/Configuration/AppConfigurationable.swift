//
//  AppConfigurationable.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation


// MARK: - AppConfigurationable

/// Service 配置协议
public protocol AppConfigurationable {
  
  /// 该方法的调用时机为：用户请求登陆接口成功(用户登陆)，Server 已经返回了当前登陆用户的用户信息。
  ///
  /// - Parameter user: 当前 Service 返回的用户信息
  func userWillLogin(user: User)
  
  
  /// 该方法的调用时机为: userWillLogin 之后且相关数据存储环境，数据库环境和网络环境已经配置好, 且用户已经完成了所有资料填写，新手课程等。在 userDidLogin 下，外部可以通过数据库拿到当前用户信息，可以进行正常的 App 内网络请求。
  ///
  /// - Parameter user: 当前 数据库 返回的用户信息
  func userDidLogin(user: User)
  
  
  /// 该方法的调用时机为: 用户请求登出接口之前。在这个方法时是最后的时机能够使用 Service 服务。如数据库或者当前用户 token 获取网络数据。
  ///
  /// - Parameter user: 当前 数据库 返回的用户信息
  func userWillLogout(user: User)
  
  /// 该方法的调用时机为: userWillLogout 之后，用户登出完成，App 配置的 Service(数据缓存，数据库和网络配置等)已经全部被清理。
  ///
  /// - Parameter user: 当前 上下文 返回的用户信息
  func userDidLogout(user: User)
  
  /// 确定该配置对于当前 App 是否是可选择的。当你定义该 `AppConfigurationable` `optional` 为 `true` 时, 意味着当前 `AppConfigurationable ` Host App 不去配置，App 也能正常运行。默认值为 `false`。
  var optional: Bool { get }
  
  /// AppConfiguration 在设置和加载每一个  `AppConfigurationable` 时，按照 `priority` 从大到小加载。默认的 `priority` 值为 `750`。
  var priority: Int { get }
  
  /// App 启动时(didFinishLaunching)会加载该方法。
  ///
  /// - Parameter user: App 启动时用户是否处于登陆状态。登陆状态的情况下，会返回 user。否则返回 nil。
  func setup(user: User?)
}

extension AppConfigurationable {
  
  public var optional: Bool {
    return false
  }
  
  public var priority: Int {
    return 750
  }
  
  public func userWillLogin(user: User) { }
  public func userDidLogin(user: User) { }
  public func userWillLogout(user: User) { }
  public func userDidLogout(user: User) { }
}

