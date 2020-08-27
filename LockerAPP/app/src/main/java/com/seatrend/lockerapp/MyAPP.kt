package com.seatrend.lockerapp

import android.app.Application
import android.content.Context

/**
 * Created by ly on 2020/8/11 11:48
 */
class MyAPP : Application() {
    companion object {
        var myApplicationContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        myApplicationContext = this
    }
}