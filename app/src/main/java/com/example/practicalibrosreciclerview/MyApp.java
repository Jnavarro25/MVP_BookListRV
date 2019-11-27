package com.example.practicalibrosreciclerview;

import android.app.Application;

public class MyApp extends Application {

    public static MyApp context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static MyApp getContext() {
        return context;
    }
}
