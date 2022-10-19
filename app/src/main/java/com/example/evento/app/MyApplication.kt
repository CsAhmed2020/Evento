package com.example.evento.app

import android.app.Application


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context =this
    }

    companion object {
        lateinit var context: MyApplication
            private set
    }

}