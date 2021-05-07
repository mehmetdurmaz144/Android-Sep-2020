package org.csystem.samples.application.samplecounterdonotdo

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.samples.application.samplecounterdonotdo.databinding.ActivityMainBinding
import java.lang.ref.WeakReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mHandler = MyHandler(this)
    private lateinit var mDateTimeTimer: Timer
    private lateinit var mCounterThread: Thread
    private var mCounter = 1L;

    private class MyHandler(mainActivity: MainActivity) : Handler(Looper.myLooper()!!) {
        private val mWeakReference = WeakReference(mainActivity)

        private fun handleCounter(mainActivity: MainActivity, msg: Message)
        {
            val counter = msg.obj.toString().toLong()

            mainActivity.mBinding.mainActivityTextViewCounter.text = counter.toString()
            Toast.makeText(mainActivity, counter.toString(), Toast.LENGTH_SHORT).show()
        }

        override fun handleMessage(msg: Message)
        {
            val mainActivity = mWeakReference.get()!!

            when (msg.what) {
                0 -> mainActivity.title = msg.obj.toString()
                1 -> handleCounter(mainActivity, msg)
                2 -> Toast.makeText(mainActivity, "Sayaç sonlandırıldı", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onStartButtonClicked()
    {
        mBinding.mainActivityButtonStart.isEnabled = false

        mCounterThread = Thread {
            try {
                while (true) {
                    mHandler.sendMessage(mHandler.obtainMessage(1, mCounter++))
                    Thread.sleep(1000)
                }
            }
            catch (ignore: InterruptedException) {
                mHandler.sendEmptyMessage(2)
            }
        }.apply {start()}
    }

    private fun dateTimeTimerCallback()
    {
        val dateTimeTimerStr = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(LocalDateTime.now())

        val msg = mHandler.obtainMessage(0, dateTimeTimerStr)

        mHandler.sendMessage(msg)
    }

    private fun initStartButton()
    {
        mBinding.mainActivityButtonStart.setOnClickListener { onStartButtonClicked() }
    }

    private fun initDateTimeTimer()
    {
        val task = object: TimerTask() {
            override fun run() = dateTimeTimerCallback()
        }
        mDateTimeTimer = Timer().apply { scheduleAtFixedRate(task, 0, 1000) }
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initViews()
    {
        initStartButton()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //Bu activity sadece PORTRAIT görünümde çalışır
    }

    private fun initialize()
    {
        initBinding()
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onPause()
    {
        if (this::mCounterThread.isInitialized)
            mCounterThread.interrupt()

        mDateTimeTimer.cancel()
        super.onPause()
    }

    override fun onResume()
    {
        initDateTimeTimer()
        if (this::mCounterThread.isInitialized)
            onStartButtonClicked()

        super.onResume()
    }

}