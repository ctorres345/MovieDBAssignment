package com.backbase.assignment.di

import com.example.data.repository.remote.MovieRemoteRepository
import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetBillboardUseCase
import com.example.domain.usecase.GetMovieDetailUseCase
import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.domain.usecase.impl.GetBillboardUseCaseImpl
import com.example.domain.usecase.impl.GetMovieDetailUseCaseImpl
import com.example.domain.usecase.impl.GetPopularMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

    @Binds
    abstract fun providesGetBillboardUseCase(getBillboardUseCase: GetBillboardUseCaseImpl): GetBillboardUseCase

    @Binds
    abstract fun providesGetPopularMoviesUseCase(getPopularMoviesUseCase: GetPopularMoviesUseCaseImpl): GetPopularMoviesUseCase

    @Binds
    abstract fun providesGetMovieDetailUseCase(getMovieDetailUseCase: GetMovieDetailUseCaseImpl): GetMovieDetailUseCase

    @Binds
    abstract fun providesMovieRepository(movieRepository: MovieRemoteRepository): MovieRepository
}