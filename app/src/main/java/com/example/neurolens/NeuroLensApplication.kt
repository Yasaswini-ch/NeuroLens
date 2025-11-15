package com.example.neurolens

import android.app.Application

class NeuroLensApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize application-level components
        instance = this
    }

    companion object {
        lateinit var instance: NeuroLensApplication
            private set
    }
}
