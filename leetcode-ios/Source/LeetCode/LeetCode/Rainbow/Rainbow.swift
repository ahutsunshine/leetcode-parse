//
//  Rainbow.swift
//  LeetCode
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation
import Rainbow
import LeetCodeService

func registerAllModules() {
  registerLeetCodeModule()
  registerLeetCodeServiceModule()
}

func getWindowRootViewController(completion: (() -> Void)? = nil) -> UIViewController {
  let tabViewController = MainTabViewController()
  tabViewController.onViewDidAppear = completion
  return tabViewController
}
