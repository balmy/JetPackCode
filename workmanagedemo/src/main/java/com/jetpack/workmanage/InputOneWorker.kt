package com.jetpack.workmanage

import android.content.Context
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author Create by yc.li09 on 2018/11/22.
 */
class InputOneWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        try {
            //模拟耗时任务
            Thread.sleep(2000)
            val inputData = inputData
            val outputData = Data.Builder().putString("key_name",
                inputData.getString("key_name")).build()
            setOutputData(outputData)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return ListenableWorker.Result.SUCCESS
    }
}
