//
//  LeetCodeStyle.swift
//  LeetCodeUI
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

public extension UIColor {
  
  @nonobjc class var fff: UIColor {
    return UIColor(white: 1.0, alpha: 1.0)
  }
  
  @nonobjc class var dft: UIColor {
    return UIColor(red: 32.0 / 255.0, green: 43.0 / 255.0, blue: 67.0 / 255.0, alpha: 1.0)
  }

}

public extension UIFont {
  class var h3: UIFont {
    return UIFont.configure(fontSize: 20, fontStyle: .pingFang_Regular)
  }
  
  class var body2: UIFont {
    return UIFont.configure(fontSize: 16, fontStyle: .pingFang_Regular)
  }
}

// Text styles

public enum FontStyle {
  case pingFang_bold
  case pingFang_Regular
  
  func fontName() -> String {
    switch self {
    case .pingFang_bold:
      return "PingFangSC-Semibold"
    case .pingFang_Regular:
      return "PingFangSC-Regular"
    }
  }
}

public extension UIFont {
  public class func configure(fontSize: CGFloat, fontStyle: FontStyle) -> UIFont {
    return UIFont(name: fontStyle.fontName(), size: fontSize)!
  }
}

