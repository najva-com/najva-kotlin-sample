package com.example.testnajvanotification

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.najva.sdk.NajvaClient
import com.najva.sdk.NajvaConfiguration

class Application : android.app.Application(){

    private var json = ""

    override fun onCreate() {
        super.onCreate()


        registerActivityLifecycleCallbacks(
            NajvaClient.getInstance(this,NajvaConfiguration().apply {
                setNajvaJsonDataListener {
                    json = it
                }
                setUserSubscriptionListener {
                    sendTokenToServer(it)
                }

                setNotificationClickListener { notificationId, buttonId ->

                }

                setReceiveNotificationListener {notificationId ->

                }

                //disableLocation()
            })
        )


    }

    private fun sendTokenToServer(it: String) {
        val queue = Volley.newRequestQueue(this)
        queue.add(StringRequest(Request.Method.POST,getUrl(),{
            Log.d("Application","token has been send to server")
        }, {
            Log.d("Application","error sending token to server")
        }))
    }

    private fun getUrl() = "https://google.com/generate_201"
}