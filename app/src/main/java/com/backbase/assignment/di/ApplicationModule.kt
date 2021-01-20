package com.backbase.assignment.di

import com.backbase.data.cache.impl.configuration.ConfigurationCache
import com.backbase.data.cache.impl.genre.GenreCache
import com.backbase.data.cache.impl.movie.MovieCache
import com.backbase.data.cache.impl.moviedetail.MovieDetailCache
import com.backbase.data.cache.room.MoviesRoomDatabase
import com.backbase.data.cache.room.RoomBuilder
import com.backbase.data.datasources.configuration.ConfigurationApi
import com.backbase.data.datasources.configuration.ConfigurationDB
import com.backbase.data.datasources.genre.GenreApi
import com.backbase.data.datasources.genre.GenreDB
import com.backbase.data.datasources.moviedetail.MovieDetailApi
import com.backbase.data.datasources.moviedetail.MovieDetailDB
import com.backbase.data.datasources.moviepage.MoviePageApi
import com.backbase.data.datasources.moviepage.MoviePageDB
import com.backbase.data.remote.MovieServices
import com.backbase.data.remote.RetrofitBuilder
import com.backbase.data.remote.impl.configuration.ConfigurationRemote
import com.backbase.data.remote.impl.genre.GenreRemote
import com.backbase.data.remote.impl.movie.MovieRemote
import com.backbase.data.remote.impl.moviedetail.MovieDetailRemote
import com.backbase.data.repository.configuration.ConfigurationRepositoryImpl
import com.backbase.data.repository.genre.GenreRepositoryImpl
import com.backbase.data.repository.movie.MovieRepositoryImpl
import com.backbase.data.repository.moviedetail.MovieDetailRepositoryImpl
import com.backbase.domain.repository.configuration.ConfigurationRepository
import com.backbase.domain.repository.genre.GenreRepository
import com.backbase.domain.repository.movie.MovieRepository
import com.backbase.domain.repository.moviedetail.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideMovieServices(builder: RetrofitBuilder): MovieServices = builder.movieServices

    @Provides
    fun provideRoomDB(builder: RoomBuilder): MoviesRoomDatabase = builder.roomDB

    @Provides
    fun provideGenreApi(genreRemote: GenreRemote) : GenreApi = genreRemote

    @Provides
    fun provideGenreDb(genreCache: GenreCache) : GenreDB = genreCache

    @Provides
    fun provideConfigurationApi(configurationRemote: ConfigurationRemote) : ConfigurationApi = configurationRemote

    @Provides
    fun provideConfigurationDb(configurationCache: ConfigurationCache) : ConfigurationDB = configurationCache

    @Provides
    fun provideMoviePageApi(moviePageRemote: MovieRemote) : MoviePageApi = moviePageRemote

    @Provides
    fun provideMoviePageDb(moviePageCache: MovieCache) : MoviePageDB = moviePageCache

    @Provides
    fun provideMovieDetailApi(movieDetailRemote: MovieDetailRemote) : MovieDetailApi = movieDetailRemote

    @Provides
    fun provideMovieDetailDb(movieDetailCache: MovieDetailCache) : MovieDetailDB = movieDetailCache
}