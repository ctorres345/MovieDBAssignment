package com.example.data.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.cache.room.tables.MovieEntity
import com.example.data.cache.room.tables.MoviePageEntity

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviePage(moviePage: MoviePageEntity): Long

    @Query("select * from movie_page where page = :page and section = :section and duedate >= :nowDate")
    fun getMoviePage(page: Int, section: String, nowDate: Long): MoviePageEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(dbMovies: List<MovieEntity>)

    @Query("select * from movie where page_id = :pageId")
    fun getMoviesFromPage(pageId: Int): List<MovieEntity>?

    @Query("DELETE FROM movie_page where section = :section")
    fun deleteAllPagesInSection(section: String)
}