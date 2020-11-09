package com.idenfyreactnative.data

import com.facebook.react.bridge.Promise

internal class IdenfyReactNativeCallbacksStoreImpl : IdenfyReactNativeCallbacksStore {
    private var currentPromise: Promise? = null
    override fun setPromise(promise: Promise) {
        currentPromise = promise
    }

    override fun getPromise(): Promise? {
        return currentPromise
    }

    override fun resetPromise() {
        currentPromise = null
    }


}
