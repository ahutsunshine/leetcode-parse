//
//  LeetCodeServiceRequirement.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import UIKit
import Rainbow

struct LeetCodeServiceRequirement: LeetCodeServiceRequiring {
  static let shared = LeetCodeServiceRequirement()
  let requirement: LeetCodeServiceRequiring? = Rainbow.shared.requirement()
}
