package com.jetpack.network.common

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

open abstract class RxBaseRequest {
    var baseUrl: String = ""

    abstract fun urlPath(): String

    fun urlParams(): Map<String, String> {
        val str: Gson = GsonBuilder().disableHtmlEscaping()
            .addSerializationExclusionStrategy(object : ExclusionStrategy {
                override fun shouldSkipField(f: FieldAttributes?): Boolean {
                    if (f?.declaringClass == RxBaseRequest::class.java) {
                        return true
                    }
                    return false
                }

                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }
            }).create()

        val type = object : TypeToken<Map<String, Any>>() {}.type
        return str.fromJson(str.toJson(this), type)
    }

    val type = object : TypeToken<Map<String, Any>>() {}.type!!
//    init {
//        initType()
//    }
//
//    private fun initType() {
//
//    }
//
//    fun getType(): TypeToken<Map<String, Any>>{}{
//        return type
//    }
}
