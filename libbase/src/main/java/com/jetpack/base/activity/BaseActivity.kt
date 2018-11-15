package com.jetpack.base.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * @author Create by yc.li09 on 2018/11/12.
 */
open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}