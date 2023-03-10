package com.example.epl.utils

import android.os.Build
import android.os.Bundle
import java.io.Serializable

fun <T : Serializable?> Bundle.deserialize(key: String, tClass: Class<T>): T? {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializable(key, tClass)
    } else {
        this.getSerializable(key) as? T
    }

}
