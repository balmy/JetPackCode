package com.jetpack.network.common

/**
 * @author Create by yc.li09 on 2018/11/29.
 */
class RxTestRequest : RxBaseRequest() {
    override fun urlPath(): String {
        return "todos/1"
    }
}