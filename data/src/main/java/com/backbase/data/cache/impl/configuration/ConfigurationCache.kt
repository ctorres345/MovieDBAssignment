package com.backbase.data.cache.impl.configuration

import com.backbase.data.cache.CacheTimestampHelper
import com.backbase.data.cache.mapper.configuration.toDomainModel
import com.backbase.data.cache.mapper.configuration.toRoomModel
import com.backbase.data.cache.room.MoviesRoomDatabase
import com.backbase.data.datasources.configuration.ConfigurationDB
import com.backbase.domain.model.configuration.Configuration
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigurationCache(
    roomDatabase: MoviesRoomDatabase,
    private val timestampHelper: CacheTimestampHelper
) : ConfigurationDB {
    @Inject constructor(roomDatabase: MoviesRoomDatabase) : this(roomDatabase, CacheTimestampHelper())

    private val imageSizesDao = roomDatabase.imageSizesDao()
    private fun CacheTimestampHelper.appConfigDueDate() = now() + appConfigRefreshTime()

    override fun getConfiguration(): Configuration? {
        val imageSizes = imageSizesDao.getImageSizes(timestampHelper.now())
        return if(imageSizes.isNotEmpty()){
            imageSizes.toDomainModel()
        } else {
            null
        }
    }

    override fun saveConfiguration(configuration: Configuration) {
        val imageSizes = configuration.toRoomModel(timestampHelper.appConfigDueDate())
        imageSizesDao.insertImageSizes(imageSizes)
    }
}