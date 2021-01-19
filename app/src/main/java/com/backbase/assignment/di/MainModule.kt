package com.backbase.assignment.di

import com.example.data.repository.configuration.ConfigurationRepositoryImpl
import com.example.data.repository.genre.GenreRepositoryImpl
import com.example.data.repository.movie.MovieRepositoryImpl
import com.example.data.repository.moviedetail.MovieDetailRepositoryImpl
import com.example.domain.repository.configuration.ConfigurationRepository
import com.example.domain.repository.genre.GenreRepository
import com.example.domain.repository.movie.MovieRepository
import com.example.domain.repository.moviedetail.MovieDetailRepository
import com.example.domain.usecase.GetGenresUseCase
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.usecase.GetMoviePageUseCase
import com.example.domain.usecase.impl.GetGenresUseCaseImpl
import com.example.domain.usecase.impl.GetMovieDetailUseCaseImpl
import com.example.domain.usecase.impl.GetMoviePageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

    @Binds
    abstract fun providesGetMovieDetailUseCase(getMovieDetailUseCase: GetMovieDetailUseCaseImpl): GetMovieDetailUseCase

    @Binds
    abstract fun providesGetMoviePageUseCase(moviePageUseCase: GetMoviePageUseCaseImpl): GetMoviePageUseCase

    @Binds
    abstract fun providesGetGenresUseCase(genresUseCase: GetGenresUseCaseImpl): GetGenresUseCase

    @Binds
    abstract fun provideConfigurationRepository(configurationRepository: ConfigurationRepositoryImpl) : ConfigurationRepository

    @Binds
    abstract fun provideGenreRepository(genreRepository: GenreRepositoryImpl) : GenreRepository

    @Binds
    abstract fun provideMovieRepository(movieRepository: MovieRepositoryImpl) : MovieRepository

    @Binds
    abstract fun provideMovieDetailRepository(movieDetailRepository: MovieDetailRepositoryImpl) : MovieDetailRepository
}