//
//  ProgressHUD.swift
//  LeetCodeUI
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import SVProgressHUD

public struct ProgressHUD {
  // Default `show` doesn't allow user interactions
  public static func show() {
    setUserinteractionEnabled(false)
    SVProgressHUD.show()
  }
  
  public static func dismiss() {
    SVProgressHUD.dismiss()
  }
  
  public static func showInfo(withStatus: String?) {
    setUserinteractionEnabled(true)
    SVProgressHUD.showInfo(withStatus: withStatus)
  }
  
  public static func showSuccess(withStatus: String?) {
    setUserinteractionEnabled(true)
    SVProgressHUD.showSuccess(withStatus: withStatus)
  }
  
  public static func showError(withStatus: String?) {
    setUserinteractionEnabled(true)
    SVProgressHUD.showError(withStatus: withStatus)
  }
  
  // Set
  private static func setUserinteractionEnabled(_ userInteractionEnabled: Bool) {
    // Allow user interactions while HUD is displayed if userInteractionEnabled
    if userInteractionEnabled {
      SVProgressHUD.setDefaultMaskType(.none)
    } else {
      SVProgressHUD.setDefaultMaskType(.clear)
    }
  }
  
}

