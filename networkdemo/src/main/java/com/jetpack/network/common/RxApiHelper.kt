package com.jetpack.network.common

import android.text.TextUtils
import android.util.Log
import com.alibaba.fastjson.JSONObject
import com.google.gson.Gson
import com.jetpack.network.ResponseWrapper
import com.jetpack.network.Tag
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Create by yc.li09 on 2018/11/29.
 */
class RxApiHelper {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): ApiComService =
            create(HttpUrl.parse(BASE_URL)!!)

        fun create(baseUrl: String): ApiComService {
            if (TextUtils.isEmpty(baseUrl)) {
                return create(HttpUrl.parse(BASE_URL)!!)
            }
            return create(HttpUrl.parse(baseUrl)!!)
        }

        private fun create(httpUrl: HttpUrl): ApiComService {

            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiComService::class.java)
        }

        fun <T> rxSend(request: RxBaseRequest) {
            create(request.baseUrl)
                .getBasicByEncapsulate(request.urlPath(), request.urlParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<com.alibaba.fastjson.JSONObject> {
                    override fun onNext(t: com.alibaba.fastjson.JSONObject) {
                        Log.d(Tag.COMMON_LOG, "onNext " + t.toString())
//                        val response : T = t.toJavaObject(request.type)
                        var response: T = Gson().fromJson(t.toJSONString(), request.type)
                        Log.d(Tag.COMMON_LOG, "onNext model " + response.toString())
//                        callback?.success(response)
                    }

                    override fun onComplete() {
                        Log.d(Tag.COMMON_LOG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(Tag.COMMON_LOG, "onSubscribe")
                    }

                    override fun onError(e: Throwable) {
                        Log.d(Tag.COMMON_LOG, "onError")
//                        callback?.failure(true, e?.message)
                    }

                })
        }

        fun <T> rxSendObservable(request: RxBaseRequest): Observable<JSONObject> {
            return create(request.baseUrl)
                .getBasicByEncapsulate(request.urlPath(), request.urlParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(handleResult())
//                .subscribe(object : Observer<com.alibaba.fastjson.JSONObject> {
//                    override fun onNext(t: com.alibaba.fastjson.JSONObject) {
//                        Log.d(Tag.COMMON_LOG, "onNext " + t.toString())
////                        val response : T = t.toJavaObject(request.type)
//                        var response : T =  Gson().fromJson(t.toJSONString(),request.type)
//                        Log.d(Tag.COMMON_LOG, "onNext model " + response.toString())
////                        callback?.success(response)
//                    }
//
//                    override fun onComplete() {
//                        Log.d(Tag.COMMON_LOG, "onComplete")
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        Log.d(Tag.COMMON_LOG, "onSubscribe")
//                    }
//
//                    override fun onError(e: Throwable) {
//                        Log.d(Tag.COMMON_LOG, "onError")
////                        callback?.failure(true, e?.message)
//                    }
//
//                })
        }

        private fun handleResult(): ObservableTransformer<in JSONObject, out JSONObject>? {
            return ObservableTransformer { upstream ->
                upstream.onErrorResumeNext(Function<Throwable, ObservableSource<JSONObject>> { t -> Observable.error(t) })
                .flatMap { t -> Observable.just(t) }
            }
        }
    }

}


