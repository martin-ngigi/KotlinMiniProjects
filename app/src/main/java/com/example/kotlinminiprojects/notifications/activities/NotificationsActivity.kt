package com.example.kotlinminiprojects.notifications.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinminiprojects.R
import com.example.kotlinminiprojects.notifications.data.NotificationData
import com.example.kotlinminiprojects.notifications.data.PushNotification
import com.example.kotlinminiprojects.notifications.retrofit.RetrofitInstance
import com.example.kotlinminiprojects.notifications.services.FirebaseService
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val TOPIC = "/topics/myTopic"

class NotificationsActivity : AppCompatActivity() {
    val TAG = "NotificationsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        /**
         * get token
         */
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
            etToken.setText(it.token)
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        btnSend.setOnClickListener{
            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            val ricepientToken = etToken.text.toString()

            if (title.isNotEmpty() && message.isNotEmpty() && ricepientToken.isNotEmpty()){
                PushNotification(
                    NotificationData(title, message),
                    //TOPIC -> this will send to all
                    ricepientToken //this will send to only one
                ).also {
                    sendNotification(it)
                }
            }
        }
    }

    private fun sendNotification(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch { 
            try {
                val response = RetrofitInstance.api.postNotification(notification)
                if(response.isSuccessful){
                    Log.d(TAG, "Response : ${Gson().toJson(response)}")
                }
                else{
                    Log.e(TAG, "Error : ${response.errorBody().toString()}")
                }
            }
            catch (e: Exception){
                Log.d(TAG, "sendNotification error : /${e.message} ")
            }
        }
}