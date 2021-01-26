package com.ediga.serviceappboundservice

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import kotlin.random.Random
import kotlin.random.nextInt


/** Command to the service to display a message  */
private const val MSG_SAY_HELLO = 1

class BoundRandomService: Service() {


    private var mMessenger: Messenger? = null
    var TAG = "BoundRandomService"
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


    private val randomNumberMessenger: Messenger = Messenger(IncomingHandler())
    /**
     * Handler of incoming messages from clients.
     */
    internal class IncomingHandler() : Handler() {
        override fun handleMessage(msg: Message) {
            var messageSendRandomNumber : Message
            Log.e(TAG, "handleMessage: "+msg.what)
            when (msg.what) {
                MSG_SAY_HELLO -> {
                    messageSendRandomNumber = Message.obtain(null, MSG_SAY_HELLO)
                    messageSendRandomNumber.arg1 = getRandomNumber()
                    try {
                        if(messageSendRandomNumber!=null) {
                            msg.replyTo.send(Message.obtain(null,
                                    MSG_SAY_HELLO))
                        }
                    } catch (e: RemoteException) {
                        Log.i(Companion.TAG, "" + e)
                    }
                }
                else -> super.handleMessage(msg)
            }
        }

         fun getRandomNumber(): Int {
            Log.e(Companion.TAG, "getRandomNumber: ")
            return Random.nextInt(0..120)
        }

        companion object {
            private const val TAG = "BoundRandomService"
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: "+getRandomNumber())

    }
    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
        Log.e(TAG, "onBind: ")
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        return randomNumberMessenger.binder
    }
    fun getRandomNumber(): Int {
        Log.e(TAG, "getRandomNumber: ")
        return Random.nextInt(0..120)
    }
}