package com.example.epl.utils

import android.content.Intent
import android.os.Build
import java.io.Serializable

fun <T : Serializable?> Intent.deserializeExtra(key: String, tClass: Class<T>): T? {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, tClass)
    } else {
        this.getSerializableExtra(key) as? T
    }

}