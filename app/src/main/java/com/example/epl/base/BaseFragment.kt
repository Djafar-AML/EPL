package com.example.epl.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.epl.MainActivity

abstract class BaseFragment : Fragment() {

    val activityHandler by lazy { activity as MainActivity }

    fun setActionbarTitle(title: String) {
        activityHandler.supportActionBar?.title = title
    }

    fun toast(msg: String) {
        Toast.makeText(activityHandler, msg, Toast.LENGTH_SHORT).show()
    }

}