package com.example.kotlinminiprojects.notifications.data

import com.example.kotlinminiprojects.notifications.data.NotificationData

data class PushNotification (
    val data: NotificationData,
    val to: String
)