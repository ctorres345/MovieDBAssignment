package com.backbase.data.cache.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_page")
data class MoviePageEntity(
    @ColumnInfo(name = "page") var page: Int,
    @ColumnInfo(name = "totalPages") var totalPages: Int,
    @ColumnInfo(name = "totalResults") var totalResults: Int,
    @ColumnInfo(name = "section") var section: String,
    @ColumnInfo(name = "duedate") var dueDate: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}