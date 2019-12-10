package com.example.practicalibrosreciclerview

import android.app.Application

internal class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context: MyApp? = null
    }
}