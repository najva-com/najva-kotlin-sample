package com.example.testnajvanotification

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.najva.sdk.NajvaClient
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


class MainActivity : AppCompatActivity() {
    var manager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        NajvaClient.configuration.setUserSubscriptionListener {
            val tv = findViewById<TextView>(R.id.textToken)
            tv.text = NajvaClient.getInstance().subscribedToken
        }
        manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val tv = findViewById<TextView>(R.id.textToken)
        tv.text = NajvaClient.getInstance().subscribedToken
    }

    fun getBasicData(): MutableMap<String, Any> {
        val map: MutableMap<String, Any> =
            HashMap()
        map["title"] = "title 2"
        map["body"] = "this is some description"
        map["onclick_action"] = "open-app"
        map["api_key"] = "8b84ad3a-3daa-4520-9adc-d7528ea95a54"
        map["url"] = "https://najva.com"
        val array = JSONArray()
        array.put(NajvaClient.getInstance().subscribedToken)
        map["subscriber_tokens"] = array
        return map
    }

    fun getHeaders(): Map<String, String> {
        val map: MutableMap<String, String> =
            HashMap()
        map["Authorization"] = "Token aff3c68af97bf4608fdf28673ca26201ca027ed9"
        return map
    }

    fun notificationLowPriority(view: View?) {
        val data = getBasicData()
        data["priority"] = "normal"
        val request: JsonObjectRequest = object : JsonObjectRequest(
            "https://app.najva.com/notification/api/v1/notifications/",
            JSONObject.wrap(data) as JSONObject,
            Response.Listener { },
            Response.ErrorListener { }
        ) {
            override fun getHeaders(): Map<String, String> {
                return this@MainActivity.getHeaders()
            }
        }
        Volley.newRequestQueue(this).add(request)
    }

    fun notificationHighPriority(view: View?) {
        val data = getBasicData()
        data["priority"] = "high"
        Log.d("NotificationData", data.toString())
        val request: JsonObjectRequest = object : JsonObjectRequest(
            "https://app.najva.com/notification/api/v1/notifications/",
            JSONObject.wrap(data) as JSONObject,
            Response.Listener { },
            Response.ErrorListener { }
        ) {
            override fun getHeaders(): Map<String, String> {
                return this@MainActivity.getHeaders()
            }
        }
        Volley.newRequestQueue(this).add(request)
    }

    fun notificationIcon(view: View?) {
        val data = getBasicData()
        data["priority"] = "high"
        data["icon"] = "https://doc.najva.com/img/najva.png"
        val request: JsonObjectRequest = object : JsonObjectRequest(
            "https://app.najva.com/notification/api/v1/notifications/",
            JSONObject.wrap(data) as JSONObject,
            Response.Listener { },
            Response.ErrorListener { }
        ) {
            override fun getHeaders(): Map<String, String> {
                return this@MainActivity.getHeaders()
            }
        }
        Volley.newRequestQueue(this).add(request)
    }

    fun notificationImage(view: View?) {
        val data = getBasicData()
        data["priority"] = "high"
        data["icon"] = "https://doc.najva.com/img/najva.png"
        data["image"] = "https://doc.najva.com/img/najva.png"
        val request: JsonObjectRequest = object : JsonObjectRequest(
            "https://app.najva.com/notification/api/v1/notifications/",
            JSONObject.wrap(data) as JSONObject,
            Response.Listener { },
            Response.ErrorListener { }
        ) {
            override fun getHeaders(): Map<String, String> {
                return this@MainActivity.getHeaders()
            }
        }
        Volley.newRequestQueue(this).add(request)
    }

    fun notificationBigText(view: View?) {
        val data = getBasicData()
        data["priority"] = "high"
        data["body"] =
            "this is some very very long description that we hope it'll be displayed well in the notification bar in every android device :)"
        val request: JsonObjectRequest = object : JsonObjectRequest(
            "https://app.najva.com/notification/api/v1/notifications/",
            JSONObject.wrap(data) as JSONObject,
            Response.Listener { },
            Response.ErrorListener { }
        ) {
            override fun getHeaders(): Map<String, String> {
                return this@MainActivity.getHeaders()
            }
        }
        Volley.newRequestQueue(this).add(request)
    }
}
