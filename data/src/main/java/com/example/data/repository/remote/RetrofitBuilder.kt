package com.example.data.repository.remote

import android.content.Context
import com.example.data.BuildConfig
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitBuilder @Inject constructor(@ApplicationContext private val context: Context) {

    private fun retrofit(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun okHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val movieServices: MovieServices by lazy {
        retrofit(BuildConfig.MOVIE_API_URL).create(MovieServices::class.java)
    }
}