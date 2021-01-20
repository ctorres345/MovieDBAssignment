package com.example.data.cache.room

import android.content.Context
import androidx.room.Room
import com.example.data.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomBuilder @Inject constructor(@ApplicationContext private val context: Context) {

    private fun roomBuilder(databaseName: String) = Room
        .databaseBuilder(context, MoviesRoomDatabase::class.java, databaseName)
        .fallbackToDestructiveMigration()
        .build()

    val roomDB: MoviesRoomDatabase by lazy {
        roomBuilder(BuildConfig.DB_NAME)
    }
}