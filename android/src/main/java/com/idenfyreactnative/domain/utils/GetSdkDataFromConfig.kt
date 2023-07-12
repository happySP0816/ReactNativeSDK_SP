package com.idenfyreactnative.domain.utils

import com.facebook.react.bridge.ReadableMap
import com.idenfy.idenfySdk.api.ui.IdenfyFaceAuthUISettings


internal object GetSdkDataFromConfig {
  fun getSdkTokenFromConfig(config: ReadableMap): String {
    return config.getString("authToken")!!
  }

  fun getFaceAuthSettingsFromConfig(config: ReadableMap): IdenfyFaceAuthUISettings {
    val faceAuthUISettings = IdenfyFaceAuthUISettings()
    val map = config.getMap("idenfyFaceAuthUISettings")

    if (map?.getBoolean("isLanguageSelectionNeeded") != null) {
      faceAuthUISettings.isLanguageSelectionNeeded = map.getBoolean("isLanguageSelectionNeeded")
    }
    if (map?.getBoolean("skipOnBoardingView") != null) {
      faceAuthUISettings.skipOnBoardingView = map.getBoolean("skipOnBoardingView")
    }
    return faceAuthUISettings
  }

  fun getImmediateRedirectFromConfig(config: ReadableMap): Boolean {
    return if (config.hasKey("withImmediateRedirect")) {
      config.getBoolean("withImmediateRedirect")
    } else {
      false
    }
  }
}
