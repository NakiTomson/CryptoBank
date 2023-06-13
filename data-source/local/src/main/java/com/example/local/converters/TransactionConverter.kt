package com.example.local.converters

import androidx.room.TypeConverter
import com.example.local.entity.TransactionDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TransactionConverter {
    @TypeConverter
    fun mapListToString(value: List<TransactionDB>): String {
        val gson = Gson()
        val type = object : TypeToken<List<TransactionDB>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun mapStringToList(value: String): List<TransactionDB> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<TransactionDB>>() {}.type
        return gson.fromJson(value, type)
    }
}