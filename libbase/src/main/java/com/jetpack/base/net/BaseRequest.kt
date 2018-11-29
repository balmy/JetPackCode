package com.jetpack.network.common

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken



/**
 * @author Create by yc.li09 on 2018/11/19.
 */
abstract class BaseRequest {
    var baseUrl: String = ""

    abstract fun urlPath(): String

    fun urlParams(): Map<String, String> {
        val str: Gson = GsonBuilder().disableHtmlEscaping()
            .addSerializationExclusionStrategy(object :ExclusionStrategy {
                override fun shouldSkipField(f: FieldAttributes?): Boolean {
                    if (f?.declaringClass == BaseRequest::class.java) {
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
}