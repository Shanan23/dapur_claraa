package com.bakery.dapurclaraa.database.repository

import androidx.annotation.WorkerThread
import com.bakery.dapurclaraa.DapurClaraaApp
import com.bakery.dapurclaraa.database.objects.Pembayaran
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

object TransactionRepository {

    private val mCustomer = MutableSharedFlow<Pembayaran>()
    val transactionFlow: Flow<Pembayaran> = mCustomer

    private val mPembayaran = MutableSharedFlow<List<Pembayaran>>()
    val transactionListFlow: Flow<List<Pembayaran>> = mPembayaran

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transactions: Pembayaran) {
        DapurClaraaApp.db.pembayaranDao().insertAll(transactions)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loadAll() {
        var transactions = DapurClaraaApp.db.pembayaranDao().getAll()
        if (transactions.isNullOrEmpty()) transactions = listOf()
        mPembayaran.emit(transactions)
    }
}