package com.bakery.dapurclaraa.database.repository

import androidx.annotation.WorkerThread
import com.bakery.dapurclaraa.DapurClaraaApp
import com.bakery.dapurclaraa.database.objects.Admin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

object AdminRepository {


    private val mAdmin = MutableSharedFlow<Admin>()
    val adminFlow: Flow<Admin> = mAdmin


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(admin: Admin) {
        DapurClaraaApp.db.adminDao().insert(admin)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun validateAdmin(name: String, pass: String) {
        var admin = DapurClaraaApp.db.adminDao().validateAdmin(name, pass)
        if (admin == null) admin = Admin()
        mAdmin.emit(admin)
//        return admin
    }
}