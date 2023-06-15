
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
        response["faceAuthenticationStatus"] = o.faceAuthenticationStatus.rawValue
        return response
    }
}
