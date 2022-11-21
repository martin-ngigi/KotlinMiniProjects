package com.example.kotlinminiprojects.viewbinding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlinminiprojects.R
import com.example.kotlinminiprojects.databinding.FragmentTestBindingBinding

class TestBindingFragment: Fragment(R.layout.fragment_test_binding) {

    /**
     * FragmentTestBindingBinding is the name of fragment + "Binding"
     */
    private lateinit var  binding: FragmentTestBindingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestBindingBinding.bind(view)
        binding.textViewHelloWord.text = "Fragment Binding"
    }

}
