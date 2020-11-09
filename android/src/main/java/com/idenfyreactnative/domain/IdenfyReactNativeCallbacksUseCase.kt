package com.idenfyreactnative.domain

import com.facebook.react.bridge.*
import com.idenfyreactnative.data.IdenfyReactNativeCallbacksStore

internal class IdenfyReactNativeCallbacksUseCase(private val idenfyReactNativeCallbacksStore: IdenfyReactNativeCallbacksStore) {

    fun setCallbacksReceiver(promise: Promise) {
        idenfyReactNativeCallbacksStore.setPromise(promise)
    }

    fun getCallbackReceiver(): Promise? {
        return idenfyReactNativeCallbacksStore.getPromise()
    }

    fun resetPromise() {
        idenfyReactNativeCallbacksStore.resetPromise()
    }


}
