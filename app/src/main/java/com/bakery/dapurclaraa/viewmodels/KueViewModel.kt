package com.bakery.dapurclaraa.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.dapurclaraa.database.objects.Kue
import com.bakery.dapurclaraa.database.repository.KueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KueViewModel : ViewModel() {
    private val TAG: String = "CustomerViewModel"

    private val _type = MutableLiveData<List<String>>()
    val type: LiveData<List<String>> get() = this._type

    private val _kues = MutableLiveData<List<Kue>>()
    val kue: LiveData<List<Kue>> get() = this._kues

    private val _kue = MutableLiveData<Kue>()
    val customer: LiveData<Kue> get() = this._kue

    init {
        // Fetch data when the ViewModel is created

        viewModelScope.launch {
            KueRepository.kueFlow.collect {
                _kues.value = it
                Log.d(TAG, "new _kue -> $it")
            }
        }
        viewModelScope.launch {
            KueRepository.typeFlow.collect {
                _type.value = it
                Log.d(TAG, "new _kue -> $it")
            }
        }
    }

    fun insertKue(kue: List<Kue>) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    KueRepository.insert(kue)
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e(TAG, "Error insertKue", e)
            }
        }
    }

    fun deleteAll(kue: List<Kue>) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    KueRepository.deleteAll(kue)
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e(TAG, "Error deleteAll", e)
            }
        }
    }

    fun loadAllKue() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    KueRepository.loadAll()
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e(TAG, "Error validateAdmin", e)
            }
        }
    }

    fun loadAllType() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    KueRepository.loadAllType()
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e(TAG, "Error validateAdmin", e)
            }
        }
    }

    fun loadAllByType(cakeType: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    KueRepository.loadAllByType(cakeType)
                }
            } catch (e: Exception) {
                // Handle exceptions if needed
                Log.e(TAG, "Error validateAdmin", e)
            }
        }
    }
}