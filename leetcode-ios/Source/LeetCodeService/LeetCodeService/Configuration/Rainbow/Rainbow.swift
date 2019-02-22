//
//  Rainbow.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Foundation
import Rainbow

public protocol LeetCodeServiceProviding: ModuleProviding { }

public protocol LeetCodeServiceRequiring: ModuleRequiring {
}

public struct AppConfigurationModule: ModuleType {
  public let moduleName: String = "LeetCodeService"
  public let providingName: String = "LeetCodeServiceProviding"
  public let providerName: String = "LeetCodeServiceProvider"
  public init() {}
}
