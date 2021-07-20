package com.example.livedatatext;

import androidx.lifecycle.LiveData;

public  class MutableLiveData<T> extends LiveData<T> {
    @Override
    public  void postValue(T value) {
        super .postValue(value);
    }

    @Override
    public  void setValue(T value) {
        super .setValue(value);
    }
}
