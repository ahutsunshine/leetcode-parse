//
//  UILabel+FontColor.swift
//  LeetCodeUI
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

public extension UILabel {
  public func configure(font: UIFont, color: UIColor) {
    textColor = color
    self.font = font
  }
  
  convenience init(font: UIFont, color: UIColor) {
    self.init()
    textColor = color
    self.font = font
  }
}
