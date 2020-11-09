package com.idenfyreactnative.data

import com.facebook.react.bridge.*

internal interface IdenfyReactNativeCallbacksStore {
    fun setPromise(promise: Promise)
    fun getPromise(): Promise?
    fun resetPromise()

}
