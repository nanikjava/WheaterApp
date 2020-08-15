package com.inspirecoding.wheaterapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather

@Dao
interface ForecastWeatherDao
{
    @Query("SELECT * FROM ForecastWeather")
    suspend fun getAllForecastWeather() : List<ForecastWeather>
    @Query ("SELECT * FROM ForecastWeather WHERE cityName = :cityName")
    fun getForecastWeather(cityName: String) : LiveData<ForecastWeather>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastWeather (forecastWeather : ForecastWeather) : Long
    @Update
    suspend fun updateForecastWeather (forecastWeather: ForecastWeather) : Int
    @Delete
    suspend fun deleteForecastWeather (forecastWeather: ForecastWeather) : Int



    @Query("SELECT COUNT(name) FROM ForecastWeather")
    fun getTableSize() : LiveData<Int>
}