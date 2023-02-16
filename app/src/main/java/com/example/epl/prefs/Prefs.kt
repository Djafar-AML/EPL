package com.example.epl.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.epl.BuildConfig
import com.example.epl.utils.edit


object Prefs {

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {

        if (!::preferences.isInitialized) {
            preferences = context.getSharedPreferences(
                "${BuildConfig.APPLICATION_ID}.EplPref",
                Context.MODE_PRIVATE
            )
        }

    }

    fun setSoccerTileIsFavorite(id: String, value: Boolean) {
        setBoolean(id, value)
    }

    fun getSoccerTileIsFavorite(id: String, defaultValue: Boolean = false): Boolean {
        return getBoolean(id, defaultValue)
    }

    private fun setBoolean(name: String, value: Boolean) {
        throwExceptionIfPrefsNotInitialized()
        preferences.edit { putBoolean(name, value) }
    }

    private fun getBoolean(name: String, defaultValue: Boolean = false): Boolean {
        throwExceptionIfPrefsNotInitialized()
        return preferences.getBoolean(name, defaultValue)
    }

    private fun throwExceptionIfPrefsNotInitialized() {
        if (!::preferences.isInitialized)
            throw Exception("First initialize the prefs object!")
    }

}