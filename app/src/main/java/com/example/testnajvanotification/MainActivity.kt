package com.example.testnajvanotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testnajvanotification.handler.MyUserHandler
import com.najva.najvasdk.Class.Najva

class MainActivity : AppCompatActivity() {

    val champaignId = 0
    val websiteId =   0
    val apikey = ""
    val locationEnables = false

    private val handlerResult : (String)-> Unit =  {
        //it is your users token
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Najva.initialize(this, champaignId, websiteId, apikey, locationEnables)

        initUserHandler()
    }

    private fun initUserHandler() {
        Najva.setUserHandler(MyUserHandler(handlerResult))
    }
}
