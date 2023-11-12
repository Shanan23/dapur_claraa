package com.bakery.dapurclaraa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.dapurclaraa.database.objects.Customers
import com.bakery.dapurclaraa.database.repository.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomerViewModel : ViewModel() {
    private val TAG: String = "CustomerViewModel"

    private val _customers = MutableLiveData<List<Customers>>()
    val customers: LiveData<List<Customers>> get() = _customers

    private val _customer = MutableLiveData<Customers>()
    val customer: LiveData<Customers> get() = _customer

    init {
        // Fetch data when the ViewModel is created

        viewModelScope.launch {
            CustomerRepository.customerFlow.collect {
                _customer.value = it
                Log.d(TAG, "new _admin -> $it")
            }
        }
    }

    fun validateCustomer(name: String, pass: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    CustomerRepository.validateCustomer(name, pass)
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e("YourViewModel", "Error validateAdmin", e)
            }
        }
    }
}