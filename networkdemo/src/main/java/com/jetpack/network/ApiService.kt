package com.jetpack.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Create by yc.li09 on 2018/11/16.
 */
interface ApiService {
    @GET("todos/1")
    fun getBasicByRxJava(): Observable<JsonModelOne>

    @GET("todos/1")
    fun getBasicByCall(): Call<JsonModelOne>
}