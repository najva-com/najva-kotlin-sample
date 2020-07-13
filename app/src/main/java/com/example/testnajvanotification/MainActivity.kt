package com.example.testnajvanotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorRed.setOnClickListener {
            drawView.color = ContextCompat.getColor(this,R.color.red)
        }

        colorBlue.setOnClickListener {
            drawView.color = ContextCompat.getColor(this,R.color.blue)
        }

        colorGreen.setOnClickListener {
            drawView.color = ContextCompat.getColor(this,R.color.green)
        }

        colorBlack.setOnClickListener {
            drawView.color = ContextCompat.getColor(this,R.color.black)
        }

    }
}
