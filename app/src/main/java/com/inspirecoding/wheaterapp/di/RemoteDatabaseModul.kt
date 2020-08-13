package com.inspirecoding.wheaterapp.di

import com.google.gson.GsonBuilder
import com.inspirecoding.wheaterapp.repository.remote.WeatherServiceAPI
import com.inspirecoding.wheaterapp.util.Common
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RemoteDatabaseModul
{
    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(Common.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun providesApi (retrofit: Retrofit) : WeatherServiceAPI
    {
        return retrofit.create(WeatherServiceAPI::class.java)
    }
}