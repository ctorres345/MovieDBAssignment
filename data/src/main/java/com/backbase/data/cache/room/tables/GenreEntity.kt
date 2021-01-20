package com.backbase.data.cache.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents an official genre for movies.
 */
@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "duedate") var dueDate: Long
)