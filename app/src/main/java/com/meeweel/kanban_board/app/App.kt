package com.meeweel.kanban_board.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.meeweel.kanban_board.app.App.AppInstance.context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }
    @SuppressLint("StaticFieldLeak")
    object AppInstance{
        lateinit var context: Context
    }
}