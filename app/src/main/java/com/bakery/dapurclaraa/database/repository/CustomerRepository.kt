package com.bakery.dapurclaraa.database.repository

import androidx.annotation.WorkerThread
import com.bakery.dapurclaraa.DapurClaraaApp
import com.bakery.dapurclaraa.database.objects.Customers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

object CustomerRepository {

    private val mCustomers = MutableSharedFlow<Customers>()
    val customerFlow: Flow<Customers> = mCustomers

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(customers: Customers) {
        DapurClaraaApp.db.customersDao().insertAll(customers)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun validateCustomer(name: String, pass: String) {
        var customer = DapurClaraaApp.db.customersDao().validateCustomer(name, pass)
        if (customer == null) customer = Customers()
        mCustomers.emit(customer)
//        return customer
    }
}