//
//  RainbowServiceConfiguration.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation
import Rainbow
import CocoaLumberjackSwift

public class RainbowServiceConfiguration: AppConfigurationable {
  
  public init(moduleContext: ModuleContext, registerAllModules: @escaping () -> Void) {
    RainbowLog.default = RainbowLog(output: { (logStr) in
      DDLogInfo(logStr)
    })
    Rainbow.shared.context = moduleContext
    registerAllModules()
  }
  
  public func setup(user: User?) {
  }
  
  public var priority: Int {
    return 100
  }
  
  public func userWillLogin(user: User) {
    Rainbow.shared.moduleWillUserLogin()
  }
  
  public func userDidLogin(user: User) {
    Rainbow.shared.moduleDidUserLogin()
  }
  
  public func userWillLogout(user: User) {
    Rainbow.shared.moduleWillUserLogout()
  }
  
  public func userDidLogout(user: User) {
    Rainbow.shared.moduleDidUserLogout()
  }
  
}

