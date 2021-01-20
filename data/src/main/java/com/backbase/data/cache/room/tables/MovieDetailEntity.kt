package com.backbase.data.cache.room.tables

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
    @ColumnInfo(name = "vote_average") var voteAverage: Double,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String?,
    @ColumnInfo(name = "runtime") var runtime: Int?,
    @ColumnInfo(name = "duedate") var dueDate: Long
)