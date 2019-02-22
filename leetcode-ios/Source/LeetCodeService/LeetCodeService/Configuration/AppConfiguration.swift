//
//  AppConfiguration.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation
import Rainbow

/// ***
/// 使用 `AppConfiguration.shared` 对当前的 Host App 进行配置。你的配置顺序应当为：
///
///在 `AppDidFinishLaunching` 中:
///
///1. registerConfigurations: 需要加载的配置和初始的 App 环境，默认为 prod

public class AppConfiguration {
  
  public static let shared = AppConfiguration()
  
  public private(set) var configurations = [AppConfigurationable]()
  public private(set) var environment: ModuleContext.Environment = .prod
  
  public func registerConfigurations(configurations: [AppConfigurationable], environment: ModuleContext.Environment = .prod) {
    self.environment = environment
    
    self.configurations = configurations.sorted { $0.priority > $1.priority }
    let user = User.current
    self.configurations.forEach { (configuration) in
      configuration.setup(user: user)
    }
  }
  
  // MARK: - Private
  
  private init() {}
  
}

extension AppConfiguration: UserLifecyleService {
  
  func userWillLogin(user: User) {
    configurations.forEach { $0.userWillLogin(user: user) }
  }
  
  func userDidLogin(user: User) {
    configurations.forEach { $0.userDidLogin(user: user) }
  }
  
  func userWillLogout(user: User) {
    configurations.forEach { $0.userWillLogout(user: user) }
  }
  
  func userDidLogout(user: User) {
    configurations.forEach { $0.userDidLogout(user: user) }
  }
  
}

