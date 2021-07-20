package com.example.databinding_viewmodel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding_viewmodel.model.Post
import com.github.javafaker.Faker
import kotlin.random.Random

class PostViewModel: ViewModel() {
    var post = MutableLiveData<Post>()

    fun click() {
        val jf = Faker()
        post.value = Post(
                Random.nextInt(100),
                jf.book().title(),
                jf.book().author()
        )
    }

    fun click2() {
        val jf = Faker()
        post.value = Post(
                Random.nextInt(100),
                jf.book().title(),
                jf.book().author()
        )
    }
}