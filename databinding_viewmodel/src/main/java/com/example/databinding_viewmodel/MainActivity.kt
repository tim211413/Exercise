package com.example.databinding_viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databinding_viewmodel.databinding.ActivityMainBinding
import com.example.databinding_viewmodel.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main
        ).apply {
            this.lifecycleOwner = this@MainActivity
            this.vm = viewModel
        }
    }
}