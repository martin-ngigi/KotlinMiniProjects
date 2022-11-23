package com.example.kotlinminiprojects.notifications.retrofit

import com.example.kotlinminiprojects.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    /**
     * lazy means we will only initialize a variable only if we need it
     */

    companion object {
        /**
         * retrofit instance
         */
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        /**
         * api will be called whenever we want to make a notification
         */
        val api by lazy {
            retrofit.create(NotificationAPI::class.java)
        }
    }
}