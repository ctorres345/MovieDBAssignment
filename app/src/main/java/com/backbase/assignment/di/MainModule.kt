package com.backbase.assignment.di

import com.backbase.data.repository.configuration.ConfigurationRepositoryImpl
import com.backbase.data.repository.movie.MovieRepositoryImpl
import com.backbase.data.repository.moviedetail.MovieDetailRepositoryImpl
import com.backbase.domain.repository.configuration.ConfigurationRepository
import com.backbase.domain.repository.movie.MovieRepository
import com.backbase.domain.repository.moviedetail.MovieDetailRepository
import com.backbase.domain.usecase.GetMovieDetailUseCase
import com.backbase.domain.usecase.GetMoviePageUseCase
import com.backbase.domain.usecase.impl.GetMovieDetailUseCaseImpl
import com.backbase.domain.usecase.impl.GetMoviePageUseCaseImpl
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
    abstract fun provideConfigurationRepository(configurationRepository: ConfigurationRepositoryImpl) : ConfigurationRepository

    @Binds
    abstract fun provideMovieRepository(movieRepository: MovieRepositoryImpl) : MovieRepository

    @Binds
    abstract fun provideMovieDetailRepository(movieDetailRepository: MovieDetailRepositoryImpl) : MovieDetailRepository
}