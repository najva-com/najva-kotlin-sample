package com.example.testnajvanotification.services

import com.google.firebase.messaging.RemoteMessage
import com.najva.najvasdk.Service.NajvaMessagingService

class MyAppService :NajvaMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        val map = remoteMessage?.data
        val jsonData = map?.get("json-data")
        //todo parse json and send parsed data through broadcast or start activity etc...
    }
}