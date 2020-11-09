//
//  GetSdkConfig.swift
//  IdenfyReactNative
//
//  Created by Viktor Vostrikov on 2020-10-13.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
import iDenfySDK
class GetSdkConfig {
    static func getAuthToken(config: NSDictionary) -> String {
        let authToken: String = config["authToken"] as! String
        return authToken
    }
}
