package com.example.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumberViewModel: ViewModel() {
    var number = 0

    // 目前 number 資料
    val currentNumber: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    // 目前 boolean 資料
    val currentBoolean: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}