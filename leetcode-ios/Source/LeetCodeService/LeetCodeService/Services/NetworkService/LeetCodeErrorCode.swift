//
//  LeetCodeErrorCode.swift
//  LeetCodeService
//
//  Created by liangliang hu on 2019/2/21.
//  Copyright © 2019 liangliang hu. All rights reserved.
//

import UIKit

public enum LeetCodeNetworkError: CustomNSError {
  case contentDataFailed
  case networkError
  case customError(message: String)
  case unknown
  
  public var errorCode: Int {
    switch self {
    case .contentDataFailed:
      return -1024
    case .networkError:
      return -1024
    case .customError(_):
      return -1024
    case .unknown:
      return -1024
    }
  }
  
  public var errorUserInfo: [String: Any] {
    switch self {
    case .contentDataFailed:
      return [NSLocalizedDescriptionKey: "请求返回的数据无效"]
    case .networkError:
      return [NSLocalizedDescriptionKey: "网络请求失败"]
    case .customError(let message):
      return [NSDebugDescriptionErrorKey: message]
    case .unknown:
      return [NSLocalizedDescriptionKey: "未知错误"]
    }
  }
}
