//
//  UILabel+Wrapping.swift
//  LeetCodeUI
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

public extension UILabel {
  public func setLineSpacing(_ textAlignment: NSTextAlignment = .left, lineSpacing: CGFloat = 0.0) {
    
    guard let labelText = self.text else { return }
    
    let paragraphStyle = NSMutableParagraphStyle()
    paragraphStyle.lineSpacing = lineSpacing
    paragraphStyle.alignment = textAlignment
    
    let attributedString: NSMutableAttributedString
    
    if let labelattributedText = self.attributedText {
      attributedString = NSMutableAttributedString(attributedString: labelattributedText)
    } else {
      attributedString = NSMutableAttributedString(string: labelText)
    }
    
    attributedString.addAttribute(.paragraphStyle, value: paragraphStyle, range: NSRange(location: 0, length: labelText.count))
    
    self.numberOfLines = 0
    self.attributedText = attributedString
  }
}
