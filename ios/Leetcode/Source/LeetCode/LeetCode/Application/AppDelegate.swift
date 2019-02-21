//
//  AppDelegate.swift
//  LeetCode
//
//  Created by liangliang hu on 2019/2/20.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import UIKit
import LeetCodeService
import Rainbow
import LeetCodeUI

@UIApplicationMain
class AppDelegate: RainbowAppDelegate {

  var window: UIWindow?


  override func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    //config main window
    window = UIWindow(frame: UIScreen.main.bounds)
    window?.backgroundColor = .white
    
    let environment: ModuleContext.Environment
    #if DEBUG || QA_BUILD
//    switch DebugUserDefault.environment {
//    case .mockData:
//      environment = .test
//    case .dev:
//      environment = .feature
//    case .staging:
//      environment = .stage
//    case .production:
//      environment = .prod
//    }
    environment = .feature
    #else
    environment = .prod
    #endif
    let moduleContext = ModuleContext(scheme: AppConstants.rainbowScheme, environment: environment, customContext: nil)
    let rainbowConfiguration = RainbowServiceConfiguration(moduleContext: moduleContext, registerAllModules: registerAllModules)
    let windowConfiguration = AppWindowConfiguration(window: &window, options: launchOptions, getMainRootViewController: MainTabViewController.init)
    //    let payServiceConfiguration = LLSPayServiceConfiguration()
    AppConfiguration.shared.registerConfigurations(configurations:
      [
       NetworkServiceConfiguration(),
       windowConfiguration,
       rainbowConfiguration,
      ], environment: environment)
    
    super.application(application, didFinishLaunchingWithOptions: launchOptions)
    
    return true
  }

  override func applicationWillResignActive(_ application: UIApplication) {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
  }

  override func applicationDidEnterBackground(_ application: UIApplication) {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
  }

  override func applicationWillEnterForeground(_ application: UIApplication) {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
  }

  override func applicationDidBecomeActive(_ application: UIApplication) {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
  }

  override func applicationWillTerminate(_ application: UIApplication) {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
  }


}

