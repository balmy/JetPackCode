package com.jetpack.network

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Create by yc.li09 on 2018/11/16.
 */
class ApiHelper {

    companion object {
        /**
         * url should end with "/"
         */
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): ApiService = create(HttpUrl.parse(BASE_URL)!!)

        fun create(baseUrl: String): ApiService = create(HttpUrl.parse(baseUrl)!!)

        private fun create(httpUrl: HttpUrl): ApiService {
//            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
//                Log.d("API", it)
//            })
//            logger.level = HttpLoggingInterceptor.Level.BODY

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

        /**
         * write less code
         */
        fun <T> compose(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable ->
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }
    }




}