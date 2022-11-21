package com.example.kotlinminiprojects.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinminiprojects.R
import com.example.kotlinminiprojects.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {

    /**
     * "ActivityViewBindingBinding" is the name of this activity + binding
     */
    private lateinit var binding: ActivityViewBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_view_binding)
        setContentView(binding.root)

        binding.textViewHelloWord.text ="View Binding Example"
    }
}