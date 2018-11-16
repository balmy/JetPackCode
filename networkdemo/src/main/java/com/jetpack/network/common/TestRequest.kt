package com.jetpack.network.common

/**
 * @author Create by yc.li09 on 2018/11/19.
 */
class TestRequest : BaseRequest() {
    override fun urlPath(): String {
        return "users/datas"
    }
}