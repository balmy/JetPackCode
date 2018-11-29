package com.jetpack.base.net

import android.text.TextUtils
import android.util.Log
import com.jetpack.base.net.ResponseWrapper
import com.jetpack.base.Tag
import com.jetpack.network.common.BaseRequest
import com.jetpack.network.common.RequestComCallback
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Create by yc.li09 on 2018/11/19.
 */
class ApiHelper {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): ApiService =
            create(HttpUrl.parse(BASE_URL)!!)

        fun create(baseUrl: String): ApiService {
            if (TextUtils.isEmpty(baseUrl)) {
                return create(HttpUrl.parse(BASE_URL)!!)
            }
            return create(HttpUrl.parse(baseUrl)!!)
        }

        private fun create(httpUrl: HttpUrl): ApiService {

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
                .create(ApiService::class.java)
        }

        fun <T>send(request: BaseRequest, callback: RequestComCallback<ResponseWrapper<T>>) {
            create(request.baseUrl)
                .getDatas(request.urlPath(), request.urlParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<com.alibaba.fastjson.JSONObject> {
                    override fun onNext(t: com.alibaba.fastjson.JSONObject) {
                        Log.d(Tag.COMMON_LOG, "onNext " + t.toString())
                        val response : ResponseWrapper<T> = t.toJavaObject(callback.type)
//                        Log.d(Tag.COMMON_LOG, "onNext model " + response.toString())
                        callback?.success(response)
                    }

                    override fun onComplete() {
                        Log.d(Tag.COMMON_LOG, "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(Tag.COMMON_LOG, "onSubscribe")
                    }

                    override fun onError(e: Throwable) {
                        Log.d(Tag.COMMON_LOG, "onError")
                        callback?.failure(true, e?.message)
                    }

                })


        }
    }

}