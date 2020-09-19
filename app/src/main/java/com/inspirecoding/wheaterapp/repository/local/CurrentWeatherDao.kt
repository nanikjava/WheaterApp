package com.inspirecoding.wheaterapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.util.Status
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao
{
    @Query ("SELECT * FROM CurrentWeather ORDER BY position ASC")
    suspend fun getAllCurrentWeather() : List<CurrentWeather>
    @Query ("SELECT * FROM CurrentWeather WHERE name = :cityName")
    fun getCurrentWeatherLiveData(cityName: String) : LiveData<CurrentWeather>

    @Query ("SELECT * FROM CurrentWeather ORDER BY Position LIMIT 1")
    fun getFirstCityWeatherSuspend() : Flow<CurrentWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather (currentWeather : CurrentWeather) : Long
    @Update
    suspend fun updateCurrentWeather (currentWeather: CurrentWeather) : Int
    @Delete
    suspend fun deleteCurrentWeather (currentWeather: CurrentWeather) : Int



    @Query("SELECT COUNT(name) FROM CurrentWeather")
    suspend fun getTableSize() : Int
}