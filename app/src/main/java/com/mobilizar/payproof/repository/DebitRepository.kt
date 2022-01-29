package com.mobilizar.payproof.repository

import androidx.lifecycle.LiveData
import com.mobilizar.payproof.data.DebitDao
import com.mobilizar.payproof.model.Debit

class DebitRepository(private val debitDao: DebitDao) {

    val readAllData: LiveData<List<Debit>> = debitDao.readAllData()

    suspend fun addDebit(debit: Debit) {
        debitDao.addDebit(debit)
    }

    suspend fun updateDebit(debit: Debit) {
        debitDao.updateDebit(debit)
    }

   suspend fun deleteDebit(debit: Debit) {
        debitDao.deleteDebit(debit)
    }

    suspend fun deleteAllDebits() {
        debitDao.deleteAllDebits()
    }


}