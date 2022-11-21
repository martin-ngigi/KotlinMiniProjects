package com.example.kotlinminiprojects

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinminiprojects.bottomnav.BottomNavigationActivity
import com.example.kotlinminiprojects.cropimage.CropMyImageActivity
import com.example.kotlinminiprojects.floatingmenu.FloatingMenuActivity
import com.example.kotlinminiprojects.notifications.activities.NotificationsActivity
import com.example.kotlinminiprojects.viewbinding.ViewBindingActivity
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

        btn_floating_menu.setOnClickListener{
            goToFloatingMenu()
        }

        btn_activity_binding.setOnClickListener {
            goToViewBinding()
        }

        btn_image_crop.setOnClickListener {
            goToImageCropping()
        }
    }

    private fun goToImageCropping() {
        Intent(this, CropMyImageActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun goToViewBinding() {
        Intent(this, ViewBindingActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun goToFloatingMenu() {
        Intent(this, FloatingMenuActivity::class.java).also {
            startActivity(it)
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