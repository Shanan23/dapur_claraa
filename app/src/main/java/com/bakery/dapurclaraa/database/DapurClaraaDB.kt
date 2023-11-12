package com.bakery.dapurclaraa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bakery.dapurclaraa.database.daos.AdminDao
import com.bakery.dapurclaraa.database.daos.CustomersDao
import com.bakery.dapurclaraa.database.daos.KueDao
import com.bakery.dapurclaraa.database.daos.PembayaranDao
import com.bakery.dapurclaraa.database.objects.Admin
import com.bakery.dapurclaraa.database.objects.Customers
import com.bakery.dapurclaraa.database.objects.Kue
import com.bakery.dapurclaraa.database.objects.Pembayaran

@Database(entities = [Admin::class, Customers::class, Kue::class, Pembayaran::class], version = 2)
abstract class DapurClaraaDB : RoomDatabase() {
    abstract fun adminDao(): AdminDao
    abstract fun customersDao(): CustomersDao
    abstract fun kueDao(): KueDao
    abstract fun pembayaranDao(): PembayaranDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        var db: DapurClaraaDB? = null

        fun getDatabase(context: Context): DapurClaraaDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return db ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, DapurClaraaDB::class.java, "DapurClaraaDB"
                ).fallbackToDestructiveMigration().build()
                db = instance
                // return instance
                instance
            }
        }
    }
}