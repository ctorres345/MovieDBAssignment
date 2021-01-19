package com.example.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.cache.room.dao.ImageSizesDAO
import com.example.data.cache.room.dao.GenreDAO
import com.example.data.cache.room.dao.MovieDAO
import com.example.data.cache.room.dao.MovieDetailDAO
import com.example.data.cache.room.tables.*

@Database(
    entities = [
        GenreEntity::class,
        MovieDetailEntity::class,
        MovieGenreEntity::class,
        MoviePageEntity::class,
        MovieEntity::class,
        ImageSizeEntity::class
    ],
    version = 1
)
abstract class MoviesRoomDatabase : RoomDatabase() {
    abstract fun movieDetailDao(): MovieDetailDAO
    abstract fun moviesDao(): MovieDAO
    abstract fun imageSizesDao(): ImageSizesDAO
    abstract fun genresDao(): GenreDAO
}