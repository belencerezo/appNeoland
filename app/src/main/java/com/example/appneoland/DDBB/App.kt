package com.example.appneoland.DDBB

import android.app.Application

class App : Application() {

    companion object {
        lateinit var database : Db
    }

    override fun onCreate() {
        super.onCreate()
        Db.getDatabase(this)
    }

}