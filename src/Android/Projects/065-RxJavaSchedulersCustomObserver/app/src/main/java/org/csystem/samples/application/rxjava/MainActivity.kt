package org.csystem.samples.application.rxjava

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.csystem.samples.application.rxjava.databinding.ActivityMainBinding
import java.util.concurrent.Executors
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun getResult(sb: StringBuilder, milliseconds: Long) : String
    {
        val value = Random.nextInt(100)

        sb.append(value).append('-')
        Thread.sleep(milliseconds)

        return sb.substring(0, sb.length - 1)
    }

    private fun getObserver() : Observer<String>
    {
        val textViewNumber = mBinding.mainActivityTextViewNumber

        return object : Observer<String> {
            override fun onSubscribe(d: Disposable?)
            {
                //...
            }

            override fun onNext(s: String?)
            {
                val text = s!!

                textViewNumber.text = text
                Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable?)
            {

            }

            override fun onComplete()
            {
                mBinding.mainActivityButtonGenerate.isEnabled = true
            }
        };
    }

    private fun onGenerateButtonClicked()
    {
        try {
            val count = mBinding.mainActivityEditTextCount.text.toString().toInt()
            val milliseconds = mBinding.mainActivityEditTextSleepMilliseconds.text.toString().toLong()
            val sb = StringBuilder()

            /*
                Örnekte Observer object'i tamamen göstermek amaçlıldır. Bu örnek açısından gerek yoktur
            */
            Observable.range(0, count)
                    .subscribeOn(Schedulers.io())
                    .map{getResult(sb, milliseconds)}
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver())

            mBinding.mainActivityButtonGenerate.isEnabled = false
        }
        catch (ex: NumberFormatException) {
            Toast.makeText(this, "Geçersiz değerler", Toast.LENGTH_LONG).show()
        }
    }

    private fun initGenerateButton()
    {
        mBinding.mainActivityButtonGenerate.setOnClickListener { onGenerateButtonClicked() };
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initGenerateButton()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}