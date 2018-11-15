package com.jetpack.lifecycledemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jump.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

        BaseLifecycleObserver().setLifecycle(lifecycle)

        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        subscribe()
    }

    private fun subscribe() {
        viewModel.getElapseTime().observe(this,
            Observer<Long> { t -> viewModelTv.text = t.toString() })
    }
}
