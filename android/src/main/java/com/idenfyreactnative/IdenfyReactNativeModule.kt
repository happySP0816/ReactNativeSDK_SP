package com.idenfyreactnative

import com.facebook.react.bridge.*
import com.idenfy.idenfySdk.CoreSdkInitialization.IdenfyController
import com.idenfy.idenfySdk.api.initialization.IdenfySettingsV2.IdenfyBuilderV2
import com.idenfyreactnative.di.DIProvider
import com.idenfyreactnative.domain.IdenfyReactNativeCallbacksUseCase
import com.idenfyreactnative.domain.IdenfySdkActivityEventListener
import com.idenfyreactnative.domain.utils.GetSdkDataFromConfig
import com.idenfy.idenfySdk.faceauthentication.api.FaceAuthenticationInitialization
import com.idenfy.idenfySdk.faceauthentication.domain.models.FaceAuthenticationType

class IdenfyReactNativeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private val diProvider = DIProvider()
    private val idenfyReactNativeCallbacksUseCase: IdenfyReactNativeCallbacksUseCase
    private val idenfySdkActivityEventListener: IdenfySdkActivityEventListener

    init {
        idenfyReactNativeCallbacksUseCase = diProvider.idenfyReactNativeCallbacksUseCase
        idenfySdkActivityEventListener = diProvider.idenfySdkActivityEventListener
        reactContext.addActivityEventListener(idenfySdkActivityEventListener)
    }


    override fun getName(): String {
        return "IdenfyReactNative"
    }

    @ReactMethod
    fun start(config: ReadableMap, promise: Promise) {
        idenfyReactNativeCallbacksUseCase.setCallbacksReceiver(promise)

        val currentActivity = currentActivity

        if (currentActivity == null) {
            idenfyReactNativeCallbacksUseCase.getCallbackReceiver()?.reject("error", Exception("Android activity does not exist"))
            idenfyReactNativeCallbacksUseCase.resetPromise()
            return
        }

        try {

            val authToken = GetSdkDataFromConfig.getSdkTokenFromConfig(config)
            val idenfySettingsV2 = IdenfyBuilderV2()
                    .withAuthToken(authToken)
                    .build()

            IdenfyController.getInstance().initializeIdenfySDKV2WithManual(currentActivity,
                    IdenfyController.IDENFY_REQUEST_CODE,
                    idenfySettingsV2)
        }

        //Unexpected exceptions
        catch (e: Throwable) {
            e.printStackTrace()
            idenfyReactNativeCallbacksUseCase.getCallbackReceiver()?.reject("error", Exception("Unexpected error. Verify that config is structured correctly."))
            idenfyReactNativeCallbacksUseCase.resetPromise()
            return
        }

    }

    @ReactMethod
        fun startFaceReAuth(config: ReadableMap, promise: Promise) {
            idenfyReactNativeCallbacksUseCase.setCallbacksReceiver(promise)

            val currentActivity = currentActivity

            if (currentActivity == null) {
                idenfyReactNativeCallbacksUseCase.getCallbackReceiver()?.reject("error", Exception("Android activity does not exist"))
                idenfyReactNativeCallbacksUseCase.resetPromise()
                return
            }

            try {

                val authToken = GetSdkDataFromConfig.getSdkTokenFromConfig(config)
                val faceReauthenticationInitialization = FaceAuthenticationInitialization(authToken, false)
                IdenfyController.getInstance().initializeFaceAuthenticationSDKV2(currentActivity,   IdenfyController.IDENFY_REQUEST_CODE, faceReauthenticationInitialization)
            }

            //Unexpected exceptions
            catch (e: Throwable) {
                e.printStackTrace()
                idenfyReactNativeCallbacksUseCase.getCallbackReceiver()?.reject("error", Exception("Unexpected error. Verify that config is structured correctly."))
                idenfyReactNativeCallbacksUseCase.resetPromise()
                return
            }

        }


}
