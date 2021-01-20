package com.backbase.data.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.backbase.data.cache.room.tables.GenreEntity

@Dao
interface GenreDAO {
    @Query("select * from genre where duedate >= :nowDate")
    fun getMovieGenres(nowDate: Long): List<GenreEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieGenres(genres: List<GenreEntity>)
}