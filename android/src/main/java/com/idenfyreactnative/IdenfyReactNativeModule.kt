package com.idenfyreactnative

import android.app.Activity
import com.facebook.react.bridge.*
import com.idenfy.idenfySdk.CoreSdkInitialization.IdenfyController
import com.idenfy.idenfySdk.api.initialization.IdenfySettingsV2.IdenfyBuilderV2
import com.idenfy.idenfySdk.api.logging.IdenfySDKLoggingSettings.IdenfySDKLoggingEnum
import com.idenfy.idenfySdk.api.ui.IdenfyUISettingsV2.IdenfyUIBuilderV2
import com.idenfyreactnative.data.IdenfyReactNativeCallbacksStoreImpl
import com.idenfyreactnative.di.DIProvider
import com.idenfyreactnative.domain.IdenfyReactNativeCallbacksUseCase
import com.idenfyreactnative.domain.IdenfySdkActivityEventListener
import com.idenfyreactnative.domain.utils.GetSdkDataFromConfig


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


}
