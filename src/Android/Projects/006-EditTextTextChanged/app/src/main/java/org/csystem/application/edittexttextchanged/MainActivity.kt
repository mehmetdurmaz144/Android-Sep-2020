package org.csystem.application.edittexttextchanged

import android.os.Bundle
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mEditTextMessage: EditText
    private lateinit var mTextViewMessage: TextView
    private lateinit var mTextViewUpperMessage: TextView

    private inner class MessageEdiTextTextWacher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
        {
            //TODO("Not yet implemented")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
        {
            s.also {
                mTextViewMessage.text = it
                mTextViewUpperMessage.text = it.toString().toUpperCase(Locale.getDefault())
            }
        }

        override fun afterTextChanged(s: Editable?)
        {
            //TODO("Not yet implemented")
        }
    }

    private fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
    {
        s.also { mTextViewMessage.text = it;  mTextViewUpperMessage.text = it.toString().toUpperCase(Locale.getDefault()) }
    }


    private fun initViews()
    {
        mEditTextMessage = findViewById(R.id.MainActivityEditTextMessage)
        mTextViewMessage = findViewById(R.id.MainActivityTextViewMessage)
        mTextViewUpperMessage = findViewById(R.id.MainActivityTextViewUpperMessage)

        mEditTextMessage.addTextChangedListener(onTextChanged = ::onTextChanged)

        //mEditTextMessage.addTextChangedListener(MessageEdiTextTextWacher())



    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }
}