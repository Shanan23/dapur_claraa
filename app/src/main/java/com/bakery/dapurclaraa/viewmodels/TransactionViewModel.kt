package com.bakery.dapurclaraa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.dapurclaraa.database.objects.Pembayaran
import com.bakery.dapurclaraa.database.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionViewModel : ViewModel() {
    private val TAG: String = "PembayaranViewModel"

    private val _transactions = MutableLiveData<List<Pembayaran>>()
    val transactions: LiveData<List<Pembayaran>> get() = _transactions

    private val _transaction = MutableLiveData<Pembayaran>()
    val transaction: LiveData<Pembayaran> get() = _transaction

    init {
        // Fetch data when the ViewModel is created

        viewModelScope.launch {
            TransactionRepository.transactionFlow.collect {
                _transaction.value = it
                Log.d(TAG, "new _transaction -> $it")
            }
        }
        viewModelScope.launch {
            TransactionRepository.transactionListFlow.collect {
                _transactions.value = it
                Log.d(TAG, "new _transaction -> $it")
            }
        }
    }

    fun insertPembayaran(transactions: Pembayaran) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    TransactionRepository.insert(transactions)
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e("YourViewModel", "Error validateAdmin", e)
            }
        }
    }

    fun loadAll() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    TransactionRepository.loadAll()
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e(TAG, "Error validateAdmin", e)
            }
        }
    }
}