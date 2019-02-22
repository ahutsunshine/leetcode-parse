//
//  LeetCodeInterface.swift
//  LeetCodeInterface
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import Rainbow

public protocol LeetCodeProviding: ModuleProviding {
  
}

// MARK: - ModuleRequiring

public protocol LeetCodeRequiring: ModuleRequiring {
  
}

// MARK: - ModuleType

public struct LeetCodeModule: ModuleType {
  public let moduleName: String = "LeetCode"
  
  public let providingName: String = "LeetCodeProviding"
  
  public let providerName: String = "LeetCodeProvider"
  
  public init() {}
}
