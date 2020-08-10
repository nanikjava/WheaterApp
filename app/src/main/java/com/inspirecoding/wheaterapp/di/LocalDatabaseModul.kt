package com.inspirecoding.wheaterapp.di

import android.app.Application
import com.inspirecoding.wheaterapp.repository.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class LocalDatabaseModul
{
    @Singleton
    @Provides
    fun provideDatabase (application: Application) = WeatherDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun providesCurrentWeatherDao (database: WeatherDatabase) = database.currentWeatherDao()

    @Singleton
    @Provides
    fun providesForecastWeatherDao (database: WeatherDatabase) = database.forecastWeatherDao()
}