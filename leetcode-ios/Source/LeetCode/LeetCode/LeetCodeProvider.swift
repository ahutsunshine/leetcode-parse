//
//  LeetCodeProvider.swift
//  LeetCode
//
//  Created by liangliang hu on 2019/2/22.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import LeetCodeUI
import LeetCodeInterface
import Rainbow
import LeetCodeService

class LeetCodeProvider: NSObject, LeetCodeProviding {
  
  static let instance: LeetCodeProviding? = Rainbow.shared.provider()
  
  required init(context: ModuleContext) { }
}
