package com.example.data.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cache.room.tables.MovieDetailEntity
import com.example.data.cache.room.tables.MovieGenreEntity

@Dao
interface MovieDetailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(dbMovieDetail: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenres(genreByMovies: List<MovieGenreEntity>)

    @Query("select * from movie_detail where _id = :detailId and duedate >= :nowDate")
    fun getMovieDetail(detailId: Int, nowDate: Long): MovieDetailEntity?

    @Query("select * from movie_genre where movie_detail_d = :movieId")
    fun getGenresForDetailId(movieId: Int): List<MovieGenreEntity>?

    @Query("DELETE FROM movie_detail")
    fun deleteAll()
}