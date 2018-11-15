package com.jetpack.lifecycledemo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import java.util.*

/**
 * @author Create by yc.li09 on 2018/11/15.
 */
class MyViewModel : ViewModel() {
    var elapsedTime: MutableLiveData<Long> = MutableLiveData()
    var initTime: Long = 0

    init {
        initTime = SystemClock.elapsedRealtime()

        var timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val value = SystemClock.elapsedRealtime() - initTime
                elapsedTime.postValue(value)
            }

        }, 1000, 1000)
    }

    fun getElapseTime(): LiveData<Long> {
        return elapsedTime
    }
}
