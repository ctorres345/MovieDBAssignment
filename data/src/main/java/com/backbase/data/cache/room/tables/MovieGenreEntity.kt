package com.backbase.data.cache.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movie_genre",
    foreignKeys = [(ForeignKey(entity = MovieDetailEntity::class,
        parentColumns = arrayOf("_id"),
        childColumns = arrayOf("movie_detail_d"),
        onDelete = ForeignKey.CASCADE))])
data class MovieGenreEntity(
    @PrimaryKey @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "movie_detail_d") var movieDetailId: Int
)