package com.mobilizar.payproof.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mobilizar.payproof.data.DebitDatabase
import com.mobilizar.payproof.model.Debit
import com.mobilizar.payproof.repository.DebitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DebitViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<Debit>>
    private val repository: DebitRepository

    init {
        val userDao = DebitDatabase.getDatabase(application).debitDao()
        repository = DebitRepository(userDao)
        readAllData = repository.readAllData
    }

    fun readAllData(): LiveData<List<Debit>> {
        return readAllData
    }

    fun addDebit(debit: Debit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDebit(debit)
        }
    }

    fun updateDebit(debit: Debit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDebit(debit)
        }
    }

    fun deleteDebit(debit: Debit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDebit(debit)
        }
    }

    fun deleteAllDebits() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllDebits()

        }
    }
}