package com.example.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TransactionDB.TABLE_NAME)
data class TransactionDB(
    @PrimaryKey val id: String,
    val cardId: String,
    val name: String,
    val media: String,
    val amount: String,
    val type: String,
    val category: String
) {
    companion object {
        const val TABLE_NAME = "bankTransaction"
    }
}