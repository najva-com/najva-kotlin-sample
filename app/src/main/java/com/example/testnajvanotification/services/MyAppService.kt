package com.example.testnajvanotification.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.najva.najvasdk.Class.NajvaPushNotificationHandler

class MyAppService : FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        NajvaPushNotificationHandler.handleMessage(applicationContext,remoteMessage)
    }

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        NajvaPushNotificationHandler.handleNewToken(applicationContext,s)
    }
}