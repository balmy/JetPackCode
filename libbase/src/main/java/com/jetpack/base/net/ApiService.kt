package com.jetpack.base.net

import com.alibaba.fastjson.JSONObject
import io.reactivex.Observable
import retrofit2.http.*

/**
 * @author Create by yc.li09 on 2018/11/28.
 */
interface ApiService {

    @GET("{url}")
    fun getDatas(@Path(value = "url", encoded = true) url:String,
                              @QueryMap params : Map<String, String>): Observable<JSONObject>

    @FormUrlEncoded
    @POST("{url}")
    fun postDatas(@Path(value = "url", encoded = true) url:String,
                      @QueryMap params : Map<String, String>): Observable<JSONObject>
}