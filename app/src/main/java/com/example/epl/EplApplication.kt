package com.example.epl

import android.app.Application

class EplApplication: Application() {

    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this

    }
}