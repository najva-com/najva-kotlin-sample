package com.example.testnajvanotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testnajvanotification.handler.MyUserHandler
import com.najva.najvasdk.Class.Najva

class MainActivity : AppCompatActivity() {

    private val handlerResult : (String)-> Unit =  {
        //it is your users token
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Najva.initialize(this, 1247443, 5768, "77d0712b-14ad-4a6a-a867-8a0e221b085d", true)

        initUserHandler()
    }

    private fun initUserHandler() {
        Najva.setUserHandler(MyUserHandler(handlerResult))
    }
}
