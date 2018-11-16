package com.jetpack.network.common

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * @author Create by yc.li09 on 2018/11/19.
 */
interface ApiComService {

    @GET("{url}")
    fun getBasicByEncapsulate(@Path(value = "url", encoded = true) url:String,
                              @QueryMap params : Map<String, String>): Observable<com.alibaba.fastjson.JSONObject>
}