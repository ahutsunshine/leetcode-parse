//
//  AppConstant.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation

public class AppConstants: NSObject {
  public static let rainbowScheme = "leetcode"
  
  public struct LeetCodeServer {
    public static let stagingBaseURLString = "localhost：8080"
    public static let productionBaseURLString = "localhost：8080"
    public static let appId = "leetcode"
    public static let certificateBundle = Bundle(for: AppConstants.self)
  }
}
