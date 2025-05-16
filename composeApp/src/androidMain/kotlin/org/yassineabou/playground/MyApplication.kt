package org.yassineabou.playground

import android.app.Application
import android.content.Context


// MyApp.kt
class MyApp : Application() {
    companion object {
        // Singleton instance
        lateinit var instance: MyApp
            private set

        // Global application context
        fun getContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}