

import Foundation
import iDenfySDK

class GetSdkConfig {
    static func getAuthToken(config: NSDictionary) -> String {
        let authToken: String = config["authToken"] as! String
        return authToken
    }
    
    static func getFaceAuthSettingsFromConfig(config: NSDictionary) -> IdenfyFaceAuthUISettings {
        let faceAuthUISettings = IdenfyFaceAuthUISettings()
        let map = config["idenfyFaceAuthUISettings"] as? NSDictionary
        
        if let unwrappedIsLanguageSelectionNeeded = map?["isLanguageSelectionNeeded"] as? Bool {
            faceAuthUISettings.isLanguageSelectionNeeded = unwrappedIsLanguageSelectionNeeded
        }
        if let unwrappedSkipOnBoardingView = map?["skipOnBoardingView"] as? Bool {
            faceAuthUISettings.skipOnBoardingView = unwrappedSkipOnBoardingView
        }
        return faceAuthUISettings
    }
    
    static func getImmediateRedirectFromConfig(config: NSDictionary) -> Bool {
        return config["withImmediateRedirect"] as? Bool == true
    }
}
