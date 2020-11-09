package com.idenfyreactnative.domain.utils

import com.facebook.react.bridge.ReadableMap


internal object GetSdkDataFromConfig {
    fun getSdkTokenFromConfig(config: ReadableMap): String {
        return config.getString("authToken")!!
    }
}
