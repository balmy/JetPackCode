package com.jetpack.network

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.jetpack.network.databinding.ActivityMainBinding
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MyTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.basicOne.setOnClickListener(this)
        binding.basicTwo.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.basic_one -> basicOneRequest()
            R.id.basic_two -> basicTwoRequest()
        }
    }

    private fun basicTwoRequest() {
        ApiHelper.create().getBasicByCall()
            .enqueue(object : Callback<JsonModelOne> {
                override fun onFailure(call: Call<JsonModelOne>, t: Throwable) {
                    Log.d(TAG, "onFailure")
                }

                override fun onResponse(call: Call<JsonModelOne>, response: Response<JsonModelOne>) {
                    Log.d(TAG, "onResponse " + response.body().toString())
                }

            })
    }

    private fun basicOneRequest() {
        ApiHelper.create().getBasicByRxJava()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<JsonModelOne> {
                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onNext(t: JsonModelOne) {
                    Log.d(TAG, "onNext " + t.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError")
                }

            })
    }
}

