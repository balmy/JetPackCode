package com.jetpack.workmanage

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        work_one.setOnClickListener { workOneTime() }
        work_two.setOnClickListener { workPeriodic() }
        work_three.setOnClickListener { workConstraint() }
    }

    private fun workConstraint() {

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
        // 设置约束条件
        val request = OneTimeWorkRequest.Builder(ConstraintWorker::class.java!!).setConstraints(constraints).build()

        WorkManager.getInstance().enqueue(request)
    }

    private fun workOneTime() {
        val inputData = Data.Builder().putString("key_name", "workOneTime").build()
        val request = OneTimeWorkRequest.Builder(InputOneWorker::class.java!!).setInputData(inputData).build()

        WorkManager.getInstance().enqueue(request)

        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id).observe(this, object : Observer<WorkInfo> {
            override fun onChanged(@Nullable workStatus: WorkInfo?) {
                if (workStatus == null) {
                    return
                }

                Log.d("myTag", workStatus!!.state.toString())
                if (workStatus!!.state === WorkInfo.State.ENQUEUED) {
                    textView.text = "任务入队"
                }
                if (workStatus!!.state === WorkInfo.State.RUNNING) {
                    textView.text ="任务正在执行"
                }
                if (workStatus!!.state.isFinished) {
                    val data = workStatus!!.outputData
                    textView.text = "任务完成" + "-结果：" + data.getString("key_name")
                }
            }
        })
    }


    private fun workPeriodic() {
        val request = PeriodicWorkRequest.Builder(
            PeriodicWorker::class.java, 10, TimeUnit.MINUTES).build()
        WorkManager.getInstance().enqueue(request)
    }
}
