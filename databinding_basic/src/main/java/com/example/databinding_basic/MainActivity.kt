package com.example.databinding_basic

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding_basic.databinding.ActivityMainBinding
import com.example.databinding_basic.model.Post
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        )
        // 建立資料
        //val post = Post(12, "Kotlin", "Java")
        // 繫結資料 Binding data
        //mBinding.post = post
    }

    fun click(view: View) {
        val post = Post(Random.nextInt(100), "Kotlin", "LiangDa")
        Toast.makeText(this, post.toString(), Toast.LENGTH_SHORT).show()
        mBinding.post = post
    }
}