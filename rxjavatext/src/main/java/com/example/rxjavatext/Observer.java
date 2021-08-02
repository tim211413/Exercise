package com.example.rxjavatext;

public interface Observer {
    void onSubscribe();

    void onNext();
    void onCompleted();
    void onError(Throwable e);
}
