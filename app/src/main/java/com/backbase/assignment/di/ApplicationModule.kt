package com.backbase.assignment.di

import com.example.data.cache.impl.configuration.ConfigurationCache
import com.example.data.cache.impl.genre.GenreCache
import com.example.data.cache.impl.movie.MovieCache
import com.example.data.cache.impl.moviedetail.MovieDetailCache
import com.example.data.cache.room.MoviesRoomDatabase
import com.example.data.cache.room.RoomBuilder
import com.example.data.datasources.configuration.ConfigurationApi
import com.example.data.datasources.configuration.ConfigurationDB
import com.example.data.datasources.genre.GenreApi
import com.example.data.datasources.genre.GenreDB
import com.example.data.datasources.moviedetail.MovieDetailApi
import com.example.data.datasources.moviedetail.MovieDetailDB
import com.example.data.datasources.moviepage.MoviePageApi
import com.example.data.datasources.moviepage.MoviePageDB
import com.example.data.remote.MovieServices
import com.example.data.remote.RetrofitBuilder
import com.example.data.remote.impl.configuration.ConfigurationRemote
import com.example.data.remote.impl.genre.GenreRemote
import com.example.data.remote.impl.movie.MovieRemote
import com.example.data.remote.impl.moviedetail.MovieDetailRemote
import com.example.data.repository.configuration.ConfigurationRepositoryImpl
import com.example.data.repository.genre.GenreRepositoryImpl
import com.example.data.repository.movie.MovieRepositoryImpl
import com.example.data.repository.moviedetail.MovieDetailRepositoryImpl
import com.example.domain.repository.configuration.ConfigurationRepository
import com.example.domain.repository.genre.GenreRepository
import com.example.domain.repository.movie.MovieRepository
import com.example.domain.repository.moviedetail.MovieDetailRepository
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