package com.jetpack.network

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * @author Create by yc.li09 on 2018/11/16.
 */
abstract class RequestCallback<T> : Observer<ResponseWrapper<T>> {

    abstract fun success(response: ResponseWrapper<T>)
    abstract fun failure(isException :Boolean, msg: String?)


    override fun onComplete() {
        Log.d(Tag.API_LOG, "onComplete")
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(response: ResponseWrapper<T>) {
        Log.d(Tag.API_LOG, "onNext")
        success(response)
    }

    override fun onError(e: Throwable) {
        failure(true, e.message)
    }
}