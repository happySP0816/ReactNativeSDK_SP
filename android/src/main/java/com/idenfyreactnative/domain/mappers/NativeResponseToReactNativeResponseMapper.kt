package com.idenfyreactnative.domain.mappers

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.idenfy.idenfySdk.api.response.*
import com.idenfy.idenfySdk.api.response.IdenfyIdentificationResult

internal class NativeResponseToReactNativeResponseMapper {

    fun map(o: IdenfyIdentificationResult): WritableMap {
        val map = Arguments.createMap()
        map.putString("autoIdentificationStatus", o.autoIdentificationStatus.status)
        map.putString("manualIdentificationStatus", o.manualIdentificationStatus.status)
        return map
    }

    fun mapFaceReauth(o: FaceReauthenticationStatus): WritableMap {
            val map = Arguments.createMap()
            map.putString("faceReauthenticationStatus", o.status)
            return map
        }
}
