package com.jetpack.lifecycledemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jump.setOnClickListener {
            val it = Intent()
            it.setClass(this, TestActivity::class.java)
            startActivity(it)
        }

        BaseLifecycleObserver().setLifecycle(lifecycle)
    }
}
