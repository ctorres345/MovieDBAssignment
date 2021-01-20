package com.example.data.cache.room.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_size")
data class ImageSizeEntity(
    @ColumnInfo(name = "base_url") var baseUrl: String,
    @ColumnInfo(name = "secure_url") var secureUrl: String,
    @ColumnInfo(name = "size") var size: String,
    @ColumnInfo(name = "image_type") val imageType: Int /* receives: poster or profile */,
    @ColumnInfo(name = "duedate") var dueDate: Long /* represents the date until the data is valid */
) {
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}