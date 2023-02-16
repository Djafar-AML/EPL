package com.example.epl

import android.app.Application
import com.example.epl.prefs.Prefs

lateinit var application: EplApplication
    private set


class EplApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initApplication()
        initPrefs()

    }

    private fun initApplication() {
        application = this
    }

    private fun initPrefs() {
        Prefs.init(application)
    }

}