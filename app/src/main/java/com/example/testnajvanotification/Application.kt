package com.example.testnajvanotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.FirebaseApp
import com.najva.sdk.NajvaClient
import com.najva.sdk.NajvaConfiguration

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(
            NajvaClient.getInstance(this, NajvaConfiguration().apply {
                setUserSubscriptionListener {
                    sendTokenToServer(it)
                }

                setNotificationClickListener { notificationId, buttonId ->

                }

                setReceiveNotificationListener { notificationId ->

                }
            })
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel("news", "News", NotificationCompat.PRIORITY_HIGH)
            createNotificationChannel("updates", "Updates", NotificationCompat.PRIORITY_DEFAULT)
            NajvaClient.configuration.setLowPriorityChannel("news")
            NajvaClient.configuration.setHighPriorityChannel("updates")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(id: String, name: String, importance: Int) {
        val channel = NotificationChannel(id, name, importance)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
        channel.setShowBadge(true)
        channel.setBypassDnd(false)

        val manager = getSystemService (NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }


    private fun sendTokenToServer(it: String) {
        val queue = Volley.newRequestQueue(this)
        queue.add(StringRequest(Request.Method.POST, getUrl(), {
            Log.d("Application", "token has been send to server")
        }, {
            Log.d("Application", "error sending token to server")
        }))
    }

    private fun getUrl() = "https://google.com/generate_201"
}