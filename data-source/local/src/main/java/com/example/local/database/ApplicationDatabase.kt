package com.example.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bsl.local.example.entity.MockEntity

@Database(entities = [MockEntity::class], version = 1, exportSchema = true)
internal abstract class ApplicationDatabase : RoomDatabase() {

    // TODO Declaring Dao's

    companion object {

        fun create(applicationContext: Context) =
            Room.databaseBuilder(
                applicationContext,
                ApplicationDatabase::class.java,
                "application",
            )
    }
}
