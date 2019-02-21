//
//  Rainbow+LeetCode.swift
//  LeetCode
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation
import LeetCodeInterface
import Rainbow

func registerLeetCodeModule() {
  Rainbow.shared.register(module: LeetCodeModule(), requirement: LeetCodeRequirement())
}

class LeetCodeRequirement: ModuleRequirementType, LeetCodeRequiring {
  public var requiringProtocolname: String {
    return "LeetCodeRequiring"
  }
  
  static let shared = LeetCodeRequirement()
  let requirement: LeetCodeRequiring? = Rainbow.shared.requirement()
}
