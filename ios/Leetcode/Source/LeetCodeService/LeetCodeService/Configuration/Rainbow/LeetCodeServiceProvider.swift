//
//  LeetCodeServiceProvider.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Rainbow

class LeetCodeServiceProvider {
  required init(context: ModuleContext) {
  }
}

extension LeetCodeServiceProvider: LeetCodeServiceProviding {
  
  func moduleLaunch(launchOptions: [UIApplication.LaunchOptionsKey : Any]?) {
  }
  
  func moduleOpenURL(url: URL, options: [UIApplication.OpenURLOptionsKey : Any]) {
  }
  
  func moduleDidEnterBackground() {
  }
  
  func moduleDidBecomeActive() {
  }
  
  func moduleWillTerminate() {
    UserDefaults.standard.synchronize()
  }
  
  func moduleWillUserLogin() {
  }
  
  func moduleDidUserLogin() {
  }
  
  func moduleDidUserLogout() {
  }
  
}

