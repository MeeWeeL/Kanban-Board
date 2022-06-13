package com.meeweel.kanban_board.app

import android.app.Application
import com.meeweel.kanban_board.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    application,
                    authorizationScreen,
                    boardsScreen,
                    tasksScreen,
                    settingsScreen
                )
            )
        }
    }
}