package com.inspirecoding.wheaterapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather

@Dao
interface ForecastWeatherDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllForecastWeather (listOfForecastWeather : List<ForecastWeather>)

    @Query("SELECT * FROM ForecastWeather")
    fun getAllForecastWeather() : LiveData<List<ForecastWeather>>
}