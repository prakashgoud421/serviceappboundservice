package com.ediga.serviceappboundservice

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View

class MainActivity : Activity() {


    private var serviceIntent: Intent? = null

    
    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceIntent = Intent(applicationContext, BoundRandomService::class.java)
        startService(serviceIntent)
    }
}