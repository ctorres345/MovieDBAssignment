package com.backbase.assignment.di

import com.example.data.repository.remote.MovieServices
import com.example.data.repository.remote.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideMovieServices(builder: RetrofitBuilder): MovieServices = builder.movieServices

}