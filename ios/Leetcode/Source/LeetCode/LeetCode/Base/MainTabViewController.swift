//
//  MainTabViewController.swift
//  LeetCode
//
//  Created by liangliang hu on 2019/2/20.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import UIKit
import Rainbow
import LeetCodeUI

class MainTabViewController: UITabBarController {
  var onViewDidAppear: (() -> Void)?
  override func viewDidLoad() {
    super.viewDidLoad()

    tabBar.tintColor = .black
    tabBar.isTranslucent = false
    
    makeViewControllers()
  }
  override func viewDidAppear(_ animated: Bool) {
    super.viewDidAppear(animated)
    
    onViewDidAppear?()
    onViewDidAppear = nil
  }
  private func makeViewControllers() {
    let problemViewController = ProblemViewController()
    problemViewController.tabBarItem.selectedImage = R.image.list()?.withRenderingMode(.alwaysOriginal)
    problemViewController.tabBarItem.image = R.image.list()?.withRenderingMode(.alwaysTemplate)
    problemViewController.tabBarItem.title = R.string.localizable.problemsTitle()
    let problemNavigationController = UINavigationController.init(rootViewController: problemViewController)
    let settingViewController = SettingViewController()
    settingViewController.tabBarItem.selectedImage = R.image.settings()?.withRenderingMode(.alwaysOriginal)
    settingViewController.tabBarItem.image = R.image.settings()?.withRenderingMode(.alwaysTemplate)
    settingViewController.tabBarItem.title = R.string.localizable.settingsTitle()
    let settingNavigationController = UINavigationController.init(rootViewController: settingViewController)
    
    viewControllers = [problemNavigationController, settingNavigationController]
  }

}
