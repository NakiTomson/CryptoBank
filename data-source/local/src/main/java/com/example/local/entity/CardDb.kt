package com.example.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = CardDb.Companion.TABLE_NAME)
data class CardDb(
    @PrimaryKey val id: String,
    val holderName: String,
    val number: String,
    val balance: String,
    val paymentType: String,
    val paymentSystem: String,
    @ColumnInfo(name = "transactions") val transactions: ArrayList<TransactionDB>
) {
    companion object {
        const val TABLE_NAME = "card"
    }
}

data class TransactionDB(
    @ColumnInfo(name = "transaction_id") val id: String,
    val cardId: String,
    val name: String,
    val media: String,
    val amount: String,
    val type: String,
    val category: String
){
    companion object{
        const val TABLE_NAME = "card"
    }
}


//abstract class Converters<T> {
//
//    @TypeConverter
//    fun mapListToString(value: List<T>): String {
//        val gson = Gson()
//        val type = object : TypeToken<List<T>>() {}.type
//        return gson.toJson(value, type)
//    }
//
//    @TypeConverter
//    fun mapStringToList(value: String): List<T> {
//        val gson = Gson()
//        val type = object : TypeToken<List<T>>() {}.type
//        return gson.fromJson(value, type)
//    }
//}

// create
class TransactionConverter {
    @TypeConverter
    fun mapListToString(value: ArrayList<TransactionDB>): String {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<TransactionDB>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): ArrayList<TransactionDB> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<TransactionDB>>() {}.type
        return gson.fromJson(value, type)
    }
}
