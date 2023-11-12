package com.bakery.dapurclaraa

import android.app.Application
import com.bakery.dapurclaraa.database.DapurClaraaDB

class DapurClaraaApp : Application() {
    companion object {
        lateinit var db: DapurClaraaDB

    }

    override fun onCreate() {
        super.onCreate()
        initDb()
    }

    fun initDb() {
        db = DapurClaraaDB.getDatabase(applicationContext)
    }
}