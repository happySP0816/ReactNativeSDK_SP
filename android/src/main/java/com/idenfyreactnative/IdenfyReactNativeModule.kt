package com.idenfyreactnative
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReadableMap
import com.idenfyreactnative.di.DIProvider
import com.idenfyreactnative.domain.IdenfyReactNativeCallbacksUseCase
import com.idenfyreactnative.domain.IdenfySdkActivityEventListener
import com.idenfyreactnative.domain.utils.GetSdkDataFromConfig
import com.idenfy.idenfySdk.CoreSdkInitialization.IdenfyController
import com.idenfy.idenfySdk.api.initialization.IdenfySettingsV2.IdenfyBuilderV2
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

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    fun multiply(a: Int, b: Int, promise: Promise) {
          promise.resolve(a * b)
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
