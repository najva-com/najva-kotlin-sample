package com.example.testnajvanotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.testnajvanotification.handler.MyUserHandler
import com.najva.najvasdk.Class.Najva
import android.provider.Settings.Secure


class MainActivity : AppCompatActivity() {

    val champaignId = 0
    val websiteId = 0
    val apikey = ""

    private val handlerResult: (String) -> Unit = {
        //TODO 'it' is your users token, send it to your server etc.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Najva.initialize(this, champaignId, websiteId, apikey)

        initUserHandler()

        initJsonHandler()
    }

    private fun initUserHandler() {
        Najva.setUserHandler(MyUserHandler(handlerResult))
    }

    private fun initJsonHandler(){
        Najva.setJsonDataListener { handleJson(it!!) }
    }

    private fun handleJson(json: String) {
        //TODO parse json
    }
}
