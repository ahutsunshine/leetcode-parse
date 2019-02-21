//
//  AppWindowConfiguration.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation
import SVProgressHUD

public class AppWindowConfiguration: AppConfigurationable {
  
  public typealias LaunchScreenHandle = (UIWindow) -> Void
  public typealias GetMainRootViewController = () -> UIViewController
  
  private let options: [UIApplication.LaunchOptionsKey: Any]?
  private var window: UIWindow?
  private let launchScreenHandle: LaunchScreenHandle?
  private let getMainRootViewController: GetMainRootViewController
  
  public init(window: inout UIWindow?, options: [UIApplication.LaunchOptionsKey: Any]?, getMainRootViewController: @escaping GetMainRootViewController, launchScreenHandle: LaunchScreenHandle? = nil) {
    window = UIWindow(frame: UIScreen.main.bounds)
    
    self.window = window
    self.options = options
    self.getMainRootViewController = getMainRootViewController
    self.launchScreenHandle = launchScreenHandle
  }
  
  public func setup(user: User?) {
    // setup ui
    LingomeAppearance.config()
    
    if let window = window {
      if options == nil {
        launchScreenHandle?(window)
      }
      window.rootViewController = getMainRootViewController()
      window.makeKeyAndVisible()
    }
  }
  
  public func userWillLogin(user: User) {
  }
  
  public func userDidLogin(user: User) {
  }
  
  public func userDidLogout(user: User) {
  }
  
  public var priority: Int {
    return 1
  }
  
}

// MARK: - UI Theme

class LingomeAppearance {
  static func config() {
    configUINavigationbarStyle()
    configUIBarButtonItemStyle()
    configSVProgressHUD()
  }
  
  fileprivate static func configUINavigationbarStyle() {
  }
  
  fileprivate static func configUIBarButtonItemStyle() {
  }
  
  fileprivate static func configSVProgressHUD() {
    SVProgressHUD.setDefaultMaskType(.clear)
    SVProgressHUD.setBackgroundColor(UIColor.black.withAlphaComponent(0.6))
    SVProgressHUD.setForegroundColor(UIColor.white)
    
    //需求统一消失时间为2秒，由于HUD的动画时间为0.3，所以设置为1.7后，可以达到2秒消失
    SVProgressHUD.setMinimumDismissTimeInterval(1.7)
    SVProgressHUD.setMaximumDismissTimeInterval(15)
  }
  
}

