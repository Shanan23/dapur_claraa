package com.bakery.dapurclaraa.database.repository

import androidx.annotation.WorkerThread
import com.bakery.dapurclaraa.DapurClaraaApp
import com.bakery.dapurclaraa.database.objects.Kue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

object KueRepository {

    private val mKues = MutableSharedFlow<List<Kue>>()
    val kueFlow: Flow<List<Kue>> = mKues

    private val mType = MutableSharedFlow<List<String>>()
    val typeFlow: Flow<List<String>> = mType

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(kue: List<Kue>) {
        DapurClaraaApp.db.kueDao().insertAll(kue)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun upsert(kue: List<Kue>) {
        DapurClaraaApp.db.kueDao().upsertAll(kue)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loadAll() {
        var kues = DapurClaraaApp.db.kueDao().getAll()
        if (kues.isNullOrEmpty()) kues = listOf()
        mKues.emit(kues)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loadAllType() {
        var types = DapurClaraaApp.db.kueDao().getAllType()
        if (types.isNullOrEmpty()) types = listOf()
        mType.emit(types)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loadAllByType(cakeType: String) {
        var types = DapurClaraaApp.db.kueDao().findByType(cakeType)
        if (types.isNullOrEmpty()) types = listOf()
        mKues.emit(types)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(kue: List<Kue>) {
        DapurClaraaApp.db.kueDao().deleteAll(kue)
    }
}