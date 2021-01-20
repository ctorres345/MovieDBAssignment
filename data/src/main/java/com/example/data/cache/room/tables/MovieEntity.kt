package com.example.data.cache.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movie",
    foreignKeys = [(ForeignKey(entity = MoviePageEntity::class,
        parentColumns = arrayOf("_id"),
        childColumns = arrayOf("page_id"),
        onDelete = ForeignKey.CASCADE))])
data class MovieEntity(
    @ColumnInfo(name = "movieId") var movieId: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "original_title") var originalTitle: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "release_date") var releaseDate: String,
    @ColumnInfo(name = "original_language") var originalLanguage: String,
    @ColumnInfo(name = "poster_path") var posterPath: String?,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String?,
    @ColumnInfo(name = "vote_count") var voteCount: Int,
    @ColumnInfo(name = "vote_average") var voteAverage: Double,
    @ColumnInfo(name = "popularity") var popularity: Double,
    @ColumnInfo(name = "page_id") var pageId: Int,
    @ColumnInfo(name = "adult") var adult: Boolean,
    @ColumnInfo(name = "video") var video: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int = 0
}