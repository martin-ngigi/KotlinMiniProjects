package com.example.kotlinminiprojects

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinminiprojects.bottomnav.BottomNavigationActivity
import com.example.kotlinminiprojects.notifications.activities.NotificationsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_notifications.setOnClickListener{
            goToNotifications()
        }

        btn_bottom_navigation.setOnClickListener{
            goToBottomNav()
        }
    }

    private fun goToBottomNav() {
        Intent(this, BottomNavigationActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun goToNotifications() {
        Intent(this, NotificationsActivity::class.java).also {
            startActivity(it)
        }
    }
}