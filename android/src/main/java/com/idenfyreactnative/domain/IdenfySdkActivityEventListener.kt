package com.idenfyreactnative.domain

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.facebook.react.bridge.BaseActivityEventListener
import com.facebook.react.bridge.WritableMap
import com.idenfy.idenfySdk.CoreSdkInitialization.IdenfyController
import com.idenfy.idenfySdk.api.response.AutoIdentificationStatus
import com.idenfy.idenfySdk.api.response.IdenfyIdentificationResult
import com.idenfy.idenfySdk.api.response.ManualIdentificationStatus
import com.idenfyreactnative.domain.mappers.NativeResponseToReactNativeResponseMapper


internal class IdenfySdkActivityEventListener(private val idenfyReactNativeCallbacksUseCase: IdenfyReactNativeCallbacksUseCase,
                                     private val nativeResponseToReactNativeResponseMapper: NativeResponseToReactNativeResponseMapper) : BaseActivityEventListener() {

    override fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val callbackReceiver = idenfyReactNativeCallbacksUseCase.getCallbackReceiver() ?: return

        if (requestCode == IdenfyController.IDENFY_REQUEST_CODE) {

            if (resultCode == IdenfyController.IDENFY_IDENTIFICATION_RESULT_CODE) {
                val idenfyIdentificationResult: IdenfyIdentificationResult = data.getParcelableExtra(IdenfyController.IDENFY_IDENTIFICATION_RESULT)
                val responseMap: WritableMap = nativeResponseToReactNativeResponseMapper.map(idenfyIdentificationResult)
                callbackReceiver.resolve(responseMap)
            }
        }

    }


}
