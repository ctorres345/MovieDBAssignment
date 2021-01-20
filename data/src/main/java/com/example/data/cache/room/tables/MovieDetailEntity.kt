package com.example.data.cache.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

/**
 * Represents the detail of a movie in the data base.
 * TODO Map countries, languanges, company and collection if needed later
 */
@Entity(tableName = "movie_detail")
data class MovieDetailEntity(
    @PrimaryKey @ColumnInfo(name = "_id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "overview") var overview: String?,
    @ColumnInfo(name = "release_date") var releaseDate: String,
    @ColumnInfo(name = "poster_path") var posterPath: String?,
    @ColumnInfo(name = "vote_count") var voteCount: Int,
    @ColumnInfo(name = "vote_average") var voteAverage: Double,
    @ColumnInfo(name = "popularity") var popularity: Double?,
    @ColumnInfo(name = "adult") var adult: Boolean,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String?,
    @ColumnInfo(name = "original_language") var originalLanguage: String,
    @ColumnInfo(name = "original_title") var originalTitle: String,
    @ColumnInfo(name = "status") var status: String,
    @ColumnInfo(name = "runtime") var runtime: Int?,
    @ColumnInfo(name = "video") var video: Boolean,
    @ColumnInfo(name = "duedate") var dueDate: Long
)