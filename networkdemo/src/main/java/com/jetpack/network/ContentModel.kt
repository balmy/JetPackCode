package com.jetpack.network

/**
 * @author Create by yc.li09 on 2018/11/16.
 * provider {"id":10000,"msg":"this is content msg","status":"SUCCESS}
 */
data class ContentModel(
    var id: Int,
    var msg: String,
    var status: String
)