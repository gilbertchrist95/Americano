package com.mokapos.americano.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Report(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Long,

    @ColumnInfo(name = "receipt_number")
    val receiptNumber: String,

    @ColumnInfo(name = "total")
    val total: Int,

    @ColumnInfo(name = "items")
    val items: String
)