package com.example.livedatatext;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public  class DemoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> myString = new MutableLiveData<> ();

    public MutableLiveData<String> getMyString(){
        return myString;
    }

    public  void setMyString(String string) {
        this .myString.setValue(string);
    }
}