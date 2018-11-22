package com.jetpack.workmanage

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        try {
            //模拟耗时任务
            Thread.sleep(1000)
            Log.d("myTag", "periodic work")
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        return ListenableWorker.Result.SUCCESS
    }

}
