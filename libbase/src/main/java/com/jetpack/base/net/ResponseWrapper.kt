package com.jetpack.base.net

/**
 * @author Create by yc.li09 on 2018/11/16.
 */
data class ResponseWrapper<T>(
    var code: Int,
    var msg: String,
    var content: T
)