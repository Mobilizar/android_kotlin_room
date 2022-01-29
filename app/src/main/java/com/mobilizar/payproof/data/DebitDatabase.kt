package com.mobilizar.payproof.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobilizar.payproof.model.Debit

@Database(entities = [Debit::class], version = 1, exportSchema = false)
abstract class DebitDatabase : RoomDatabase() {
    abstract fun debitDao(): DebitDao

    companion object {
        @Volatile
        private var INSTANCE: DebitDatabase? = null

        fun getDatabase(context: Context): DebitDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DebitDatabase::class.java,
                    "debit_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}