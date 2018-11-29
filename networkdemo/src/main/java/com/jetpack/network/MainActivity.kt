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
import com.alibaba.fastjson.JSONObject
import com.jetpack.network.common.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.basicOne.setOnClickListener(this)
        binding.basicTwo.setOnClickListener(this)
        binding.basicThree.setOnClickListener(this)
        binding.basicFour.setOnClickListener(this)
        binding.basicFive.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.basic_one -> basicOneRequest()
            R.id.basic_two -> basicTwoRequest()
            R.id.basic_three -> basicThreeRequest()
            R.id.basic_four -> basicFourRequest()
            R.id.basic_five -> basicFiveRequest()
            else -> {

            }
        }
    }

    private fun basicFiveRequest() {
        val request = RxTestRequest()
//        RxApiHelper.rxSend<JsonModelOne>(request)
        RxApiHelper.rxSendObservable<JsonModelOne>(request)
            .subscribe(object: Observer<JSONObject>{
                override fun onComplete() {
                    Log.d(Tag.COMMON_LOG, "main onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(Tag.COMMON_LOG, "main onSubscribe")
                }

                override fun onNext(t: JSONObject) {
                    Log.d(Tag.COMMON_LOG, "main onNext " + t.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d(Tag.COMMON_LOG, "main onError")
                }

            })
    }

    private fun basicFourRequest() {
        val request:BaseRequest = TestRequest()
        request.baseUrl= "http://172.28.48.21:3000/"
        ApiComHelper.send(request, object : RequestComCallback<ResponseWrapper<ContentModel>>() {
            override fun success(response: ResponseWrapper<ContentModel>) {
               Log.d(Tag.COMMON_LOG, "activity " + response.toString())
            }

            override fun failure(isException: Boolean, msg: String?) {

            }

        })
    }

    private fun basicThreeRequest() {
        ApiHelper.create("http://172.28.48.21:3000/").getBasicByFrame()
            .compose(ApiHelper.compose())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : RequestCallback<ContentModel>() {
                override fun success(response: ResponseWrapper<ContentModel>) {
                    Log.d(Tag.API_LOG, "success" + response.content.toString())
                }

                override fun failure(isException: Boolean, msg: String?) {
                    Log.d(Tag.API_LOG, "failure")
                }
            })
    }

    private fun basicTwoRequest() {
        ApiHelper.create().getBasicByCall()
            .enqueue(object : Callback<JsonModelOne> {
                override fun onFailure(call: Call<JsonModelOne>, t: Throwable) {
                    Log.d(Tag.COMMON_LOG, "onFailure")
                }

                override fun onResponse(call: Call<JsonModelOne>, response: Response<JsonModelOne>) {
                    Log.d(Tag.COMMON_LOG, "onResponse " + response.body().toString())
                }

            })
    }

    private fun basicOneRequest() {
        ApiHelper.create().getBasicByRxJava()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<JsonModelOne> {
                override fun onComplete() {
                    Log.d(Tag.COMMON_LOG, "onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.d(Tag.COMMON_LOG, "onSubscribe")
                }

                override fun onNext(t: JsonModelOne) {
                    Log.d(Tag.COMMON_LOG, "onNext " + t.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d(Tag.COMMON_LOG, "onError")
                }

            })
    }
}

