package com.example.request_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class buton_request : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buton_request)
        val registerButton = findViewById<Button>(R.id.request)

        registerButton.setOnClickListener {
            val intent = Intent(this, API::class.java)
            startActivity(intent)
        }
    }
}