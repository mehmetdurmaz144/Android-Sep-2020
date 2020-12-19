package org.csystem.application.simplepersoninfo

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.application.simplepersoninfo.model.Person
import org.csystem.application.simplepersoninfo.model.global

class MainActivity : AppCompatActivity() {

    private lateinit var mEditTextName : EditText
    private lateinit var mEditTextPhone: EditText
    private lateinit var mEditTextCitizenID : EditText
    private lateinit var mViewTextPersonInfo : TextView

    private fun getPerson() : Person
    {
        var name = mEditTextName.text.toString()
        var phone = mEditTextName.text.toString()
        var citizenId = mEditTextCitizenID.toString()

        if(!PersonValidityUtil.Validate(name, phone, citizenId)

        return Person(name, citizenId, phone)
    }


    private fun clearEditTexts()
    {
        mEditTextName.setText("")
        mEditTextCitizenID.setText("")
        mEditTextPhone.setText("")
    }

    private fun clearAll()
    {
        clearEditTexts()
        mViewTextPersonInfo.text = ""
    }

    private fun initTextViews()
    {
        mViewTextPersonInfo = findViewById(R.id.mainActivityTextViewPerson)
    }


    private fun initEditTexts()
    {
        mEditTextCitizenID = findViewById(R.id.mainActivityEditTextCitizenId)
        mEditTextName = findViewById(R.id.mainActivityEditTextName)
        mEditTextPhone = findViewById(R.id.mainActivityEditTextPhone)
    }

    private fun initialize()
    {
        initTextViews()
        initEditTexts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun onSaveButtonClicked(view: View)
    {
        try {

        }
        catch (ex: Throwable) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show()
        }


    }

    fun onClearButtonClicked(view: View) = clearEditTexts()
    }

    fun onExitButtonClicked(view: View)
    {

    }
}