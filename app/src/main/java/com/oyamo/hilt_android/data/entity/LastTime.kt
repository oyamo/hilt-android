package com.oyamo.hilt_android.data.entity

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class LastTime @Inject constructor(var context: Context) {

    private val lastTimeSharedPreferences: SharedPreferences =
        context.getSharedPreferences("last-time", Context.MODE_PRIVATE)

    private val LAST_TIME_LABEL = "LAST_TIME_LOGGED_IN"

    fun recordLastTime() {
        val lastTime = System.nanoTime()
        val editor = lastTimeSharedPreferences.edit()
        editor.putLong(LAST_TIME_LABEL, lastTime)
        editor.apply()
    }

    fun getLastTimeLoggedIn() = lastTimeSharedPreferences.getLong(LAST_TIME_LABEL, -1)
}