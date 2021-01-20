package com.backbase.data.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.backbase.data.cache.room.tables.ImageSizeEntity

@Dao
interface ImageSizesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageSizes(sizes: List<ImageSizeEntity>)

    @Query("select * from image_size where duedate >= :nowDate")
    fun getImageSizes(nowDate: Long): List<ImageSizeEntity>
}