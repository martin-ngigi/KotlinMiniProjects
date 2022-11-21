package com.example.kotlinminiprojects.notifications.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.kotlinminiprojects.R
import com.example.kotlinminiprojects.notifications.activities.NotificationsActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

/**
 * for receiving services in the background
 */

private const val CHANNEL_ID = "my_channel"
class FirebaseService: FirebaseMessagingService() {

    companion object{
        /**
         * save token in shared prefernces
         *
         */
        var sharedPref : SharedPreferences?= null

        var token: String?
        get(){
            return sharedPref?.getString("token", "") //if empty, return empty string
        }
        set(value){
            sharedPref?.edit()?.putString("token", value)?.apply() //save token
        }

    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)

        token = newToken
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        /**
         * whenever we receive a notification, then show it
         */

        /**
         * open NotificationActivity when notification is received
         */
        val intent = Intent(this, NotificationsActivity::class.java)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        /**
         * create a unique id for each notification. this will make sure that whenever there is a new notification, it wont override the previous one
         */
        val notificationID = Random.nextInt()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(notificationManager)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        /**
         * FLAG_ONE_SHOT means notification can only be used once
         */
        val pendingInt = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.ic_baseline_account_box_24)
            .setAutoCancel(true)
            .setContentIntent(pendingInt)
            .build()

        /**
         * show notification
         */
        notificationManager.notify(notificationID,notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channelName = "channelName"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel notification"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }
}