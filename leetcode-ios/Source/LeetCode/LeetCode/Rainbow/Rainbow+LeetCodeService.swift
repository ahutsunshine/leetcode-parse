//
//  Rainbow+LeetCodeService.swift
//  LeetCode
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import LeetCodeService
import Rainbow

func registerLeetCodeServiceModule() {
  Rainbow.shared.register(module: AppConfigurationModule(), requirement: LeetCodeServiceRequirement())
}

struct LeetCodeServiceRequirement: ModuleRequirementType, LeetCodeServiceRequiring {
  var requiringProtocolname: String {
    return "LeetCodeServiceRequiring"
  }
}

