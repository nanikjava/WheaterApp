package com.inspirecoding.wheaterapp.di

import com.inspirecoding.wheaterapp.repository.sharedPreferences.SharedPreferencesRepository
import com.inspirecoding.wheaterapp.repository.sharedPreferences.SharedPreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class SharedPreferencesModule
{
    @Binds
    abstract fun providesWeatherRepository (
        sharedPreferencesRepositoryImpl: SharedPreferencesRepositoryImpl
    ) : SharedPreferencesRepository
}