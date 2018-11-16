package com.jetpack.network.common

import com.alibaba.fastjson.TypeReference
import com.jetpack.network.ResponseWrapper

/**
 * @author Create by yc.li09 on 2018/11/19.
 */
abstract class RequestComCallback<T: ResponseWrapper<*>> : TypeReference<T>() {
    abstract fun success(response: T)
    abstract fun failure(isException :Boolean, msg: String?)
}
