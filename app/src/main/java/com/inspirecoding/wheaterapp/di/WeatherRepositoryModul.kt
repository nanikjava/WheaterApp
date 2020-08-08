package com.inspirecoding.wheaterapp.di

import com.inspirecoding.wheaterapp.repository.WeatherRepository
import com.inspirecoding.wheaterapp.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn (ApplicationComponent::class)
@Module
abstract class WeatherRepositoryModul
{
    @Binds
    abstract fun providesWeatherRepository (
        weatherRepositoryImpl: WeatherRepositoryImpl
    ) : WeatherRepository
}