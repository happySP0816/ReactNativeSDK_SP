//
//  NativeResponseToReactNativeResponseMapper.swift
//  IdenfyReactNative
//
//  Created by Viktor Vostrikov on 2020-10-20.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
import iDenfySDK
typealias RNResponse = [String: String]
struct NativeResponseToReactNativeResponseMapper {
    static func map(o: IdenfyIdentificationResult) -> RNResponse {
        var response = RNResponse()
        response["autoIdentificationStatus"] = o.autoIdentificationStatus.rawValue
        response["manualIdentificationStatus"] = o.manualIdentificationStatus.rawValue
        return response
    }

    static func mapFaceReauth(o: FaceAuthenticationResult) -> RNResponse {
            var response = RNResponse()
            response["faceReauthenticationStatus"] = o.faceAuthenticationStatus.rawValue
            response["faceAuthenticationStatus"] = o.faceAuthenticationStatus.rawValue
            return response
        }
}
