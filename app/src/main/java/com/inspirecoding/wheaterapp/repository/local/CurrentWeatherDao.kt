package com.inspirecoding.wheaterapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inspirecoding.wheaterapp.model.CurrentWeather

@Dao
interface CurrentWeatherDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrentWeather (listOfWeather : CurrentWeather)

    @Query ("SELECT * FROM CurrentWeather")
    fun getAllCurrentWeather() : LiveData<List<CurrentWeather>>
}