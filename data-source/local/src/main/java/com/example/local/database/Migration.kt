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

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE card ADD COLUMN designCard TEXT NOT NULL DEFAULT ( '' )")
    }
}
val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE card RENAME COLUMN designCard TO background")
        database.execSQL("ALTER TABLE card ADD COLUMN textColor TEXT NOT NULL DEFAULT ( '' )")
        database.execSQL("ALTER TABLE card ADD COLUMN paySystemLogo TEXT NOT NULL DEFAULT ( '' )")
    }
}
