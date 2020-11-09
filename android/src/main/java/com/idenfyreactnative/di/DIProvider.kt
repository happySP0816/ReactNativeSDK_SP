package com.idenfyreactnative.di

import com.idenfyreactnative.data.IdenfyReactNativeCallbacksStoreImpl
import com.idenfyreactnative.domain.IdenfyReactNativeCallbacksUseCase
import com.idenfyreactnative.domain.IdenfySdkActivityEventListener
import com.idenfyreactnative.domain.mappers.NativeResponseToReactNativeResponseMapper

internal class DIProvider {
    val idenfySdkActivityEventListener: IdenfySdkActivityEventListener by lazy {
        IdenfySdkActivityEventListener(idenfyReactNativeCallbacksUseCase, nativeResponseToReactNativeResponseMapper)
    }
    val idenfyReactNativeCallbacksUseCase by lazy {
        IdenfyReactNativeCallbacksUseCase(idenfyReactNativeCallbacksStore)
    }

    private val nativeResponseToReactNativeResponseMapper by lazy {
        NativeResponseToReactNativeResponseMapper()
    }

    private val idenfyReactNativeCallbacksStore by lazy {
        IdenfyReactNativeCallbacksStoreImpl()
    }
}