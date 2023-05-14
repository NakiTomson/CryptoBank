package com.example.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserDb.TABLE_NAME)
data class UserDb(
    @PrimaryKey val id: String,
    val email: String,
    val type: String
) {

    companion object {
        const val TABLE_NAME = "user"
    }
}