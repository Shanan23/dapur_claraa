package com.bakery.dapurclaraa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.dapurclaraa.database.objects.Admin
import com.bakery.dapurclaraa.database.repository.AdminRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdminViewModel : ViewModel() {

    private val TAG: String = "AdminViewModel"
    private val _admins = MutableLiveData<List<Admin>>()
    val admins: LiveData<List<Admin>> get() = _admins

    private val _admin = MutableLiveData<Admin>()
    val admin: LiveData<Admin> get() = _admin

    init {
        // Fetch data when the ViewModel is created

        viewModelScope.launch {
            AdminRepository.adminFlow.collect {
                _admin.value = it
                Log.d(TAG, "new _admin -> $it")
            }
        }
    }

    fun insertAdmin(admin: Admin) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    AdminRepository.insert(admin)
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e("YourViewModel", "Error validateAdmin", e)
            }
        }
    }

    fun validateAdmin(name: String, pass: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    AdminRepository.validateAdmin(name, pass)

                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                e.printStackTrace()
                Log.e("YourViewModel", "Error validateAdmin", e)
            }
        }

    }
}