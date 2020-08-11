package com.inspirecoding.wheaterapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.util.Status

@Dao
interface CurrentWeatherDao
{
    @Query ("SELECT * FROM CurrentWeather ORDER BY position ASC")
    fun getAllCurrentWeather() : LiveData<List<CurrentWeather>>
    @Query ("SELECT * FROM CurrentWeather WHERE name = :cityName")
    fun getCurrentWeather(cityName: String) : LiveData<CurrentWeather>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather (currentWeather : CurrentWeather) : Long
    @Update
    suspend fun updateCurrentWeather (currentWeather: CurrentWeather) : Int
    @Delete
    suspend fun deleteCurrentWeather (currentWeather: CurrentWeather) : Int



    @Query("SELECT COUNT(name) FROM CurrentWeather")
    fun getTableSize() : LiveData<Int>
}