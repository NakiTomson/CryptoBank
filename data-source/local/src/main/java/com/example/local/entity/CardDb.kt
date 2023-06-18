package com.example.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


@Entity(tableName = CardDb.TABLE_NAME)
data class CardDb(
    @PrimaryKey val id: String,
    val holderName: String,
    val number: String,
    val balance: String,
    val paymentType: String,
    val paymentSystem: String,
    @Embedded val designCard: DesignCardDb,
) {
    companion object {
        const val TABLE_NAME = "card"
    }
}

class DesignCardDb(
    val background: String,
    val textColor: String,
    val paySystemLogo: String
)

