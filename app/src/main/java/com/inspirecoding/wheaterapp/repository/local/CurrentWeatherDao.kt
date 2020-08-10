package com.inspirecoding.wheaterapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.util.Status

@Dao
interface CurrentWeatherDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather (currentWeather : CurrentWeather) : Long

    @Query ("SELECT * FROM CurrentWeather ORDER BY position ASC")
    fun getAllCurrentWeather() : LiveData<List<CurrentWeather>>

    @Query ("SELECT * FROM CurrentWeather WHERE name = :cityName")
    fun getCurrentWeather(cityName: String) : LiveData<CurrentWeather>

    @Query("SELECT COUNT(name) FROM CurrentWeather")
    fun getTableSize() : LiveData<Int>
}