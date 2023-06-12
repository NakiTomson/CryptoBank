package com.example.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE user(id TEXT NOT NULL,email TEXT NOT NULL, type TEXT NOT NULL, PRIMARY KEY(id))")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE user ADD COLUMN name TEXT NOT NULL")
        database.execSQL("ALTER TABLE user ADD COLUMN avatar TEXT NOT NULL")
    }
}