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
import java.lang.Exception
import com.idenfy.idenfySdk.api.response.*


internal class IdenfySdkActivityEventListener(private val idenfyReactNativeCallbacksUseCase: IdenfyReactNativeCallbacksUseCase,
                                     private val nativeResponseToReactNativeResponseMapper: NativeResponseToReactNativeResponseMapper) : BaseActivityEventListener() {

    override fun onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val callbackReceiver = idenfyReactNativeCallbacksUseCase.getCallbackReceiver() ?: return

        if (requestCode == IdenfyController.IDENFY_REQUEST_CODE) {

            if (resultCode == IdenfyController.IDENFY_IDENTIFICATION_RESULT_CODE) {
                try {
                    val idenfyIdentificationResult: IdenfyIdentificationResult? = data?.getParcelableExtra(IdenfyController.IDENFY_IDENTIFICATION_RESULT)
                    if(idenfyIdentificationResult==null){
                        callbackReceiver.reject("error", Exception("Data is null"))
                        idenfyReactNativeCallbacksUseCase.resetPromise()
                        return
                    }
                    val responseMap: WritableMap = nativeResponseToReactNativeResponseMapper.map(idenfyIdentificationResult)
                    callbackReceiver.resolve(responseMap)
                    idenfyReactNativeCallbacksUseCase.resetPromise()
                }
                catch (e:Exception){
                    callbackReceiver.reject("error", Exception("An error occurred serializing results.$e"))
                    idenfyReactNativeCallbacksUseCase.resetPromise()
                }
            }
            else if (resultCode == IdenfyController.IDENFY_FACE_AUTHENTICATION_RESULT_CODE) {
                            val faceReauthenticationResult: FaceAuthenticationResult? =
                                data?.getParcelableExtra(IdenfyController.IDENFY_FACE_AUTHENTICATION_RESULT)
                                if(faceReauthenticationResult==null){
                                callbackReceiver.reject("error", Exception("Data is null"))
                                idenfyReactNativeCallbacksUseCase.resetPromise()
                                return
                                }
                                val responseMap: WritableMap = nativeResponseToReactNativeResponseMapper.mapFaceReauth(faceReauthenticationResult.faceAuthenticationStatus)
                                callbackReceiver.resolve(responseMap)
                                 idenfyReactNativeCallbacksUseCase.resetPromise()

                        }
        }

    }


}
