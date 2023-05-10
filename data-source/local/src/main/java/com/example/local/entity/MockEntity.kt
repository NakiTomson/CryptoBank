package com.bsl.local.example.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Mock entity to avoid build errors, remove after you add your entity or remove the room database.
@Entity(tableName = "mocks")
data class MockEntity(@PrimaryKey val id: Int)
