

import Foundation
import iDenfySDK
class GetSdkConfig {
    static func getAuthToken(config: NSDictionary) -> String {
        let authToken: String = config["authToken"] as! String
        return authToken
    }
}
