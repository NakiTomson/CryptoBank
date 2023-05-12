package com.example.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.local.entity.OnBoardingDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class OnBoardingDb(
    val title: String,
    val text: String,
    val media: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) {
    companion object {
        const val TABLE_NAME = "OnBoarding"
    }
}
