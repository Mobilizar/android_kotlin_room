package com.mobilizar.payproof.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "debit_table")
data  class  Debit(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val description: String,
    @ColumnInfo val paid: Boolean,

): Parcelable
