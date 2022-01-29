package com.mobilizar.payproof.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobilizar.payproof.model.Debit

@Dao
interface DebitDao {

    @Update
    suspend fun updateDebit(vararg debit: Debit)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDebit(vararg debit: Debit)

    @Delete
    suspend fun deleteDebit(debit: Debit)

    @Query("DELETE FROM debit_table")
    suspend fun deleteAllDebits()

    @Query("SELECT * FROM debit_table ORDER BY description ASC")
    fun readAllData(): LiveData<List<Debit>>
}