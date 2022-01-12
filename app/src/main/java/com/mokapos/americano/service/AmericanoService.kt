package com.mokapos.americano.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mokapos.americano.data.ProductDao
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import javax.inject.Inject

@AndroidEntryPoint
class AmericanoService : FirebaseMessagingService() {

    @Inject
    lateinit var productDao: ProductDao

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e(TAG, "From: ${remoteMessage.from}")

        remoteMessage.data.let {
            val data = remoteMessage.data.values.first().split(";")
            Log.e(TAG, "Message data payload: " + data.toString())
            modifyData(data)
        }


    }

    private fun modifyData(data: List<String>) {
        if (data.first() == "U") {//update
            val tableName = data[1]
            val columnName = data[2]
            val from = data[3]
            val to = data[4]

            Executors.newSingleThreadExecutor().submit {
                productDao.updateName(from, to)
            }
        }
    }

    //c3XB6k8nTaC_Oy6y0B9R0A:APA91bGSr5RIHUY_wpWpJYRl8836UMdEdq3yzlwL-lg4cJc-5UkXmKpGc5U4DWgv7clXhEO65kVkV5piX-T4zMS9z4pVhxuxpVy74yqt3gkRgO2eh64qQHKjtG41K3ICTZqSwj0S1ux9
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(TAG, "Refreshed token: $token")
    }

}

const val TAG = "Americano"

/*
*  remoteMessage.notification.let {
            Log.e(TAG, "Message notification body: " + remoteMessage.notification?.body)
            Log.e(TAG, "Message notification text: " + remoteMessage.notification?.title)
        }
* */