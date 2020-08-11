package com.inspirecoding.wheaterapp.repository

import androidx.lifecycle.LiveData
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.Resource
import retrofit2.Response
import retrofit2.http.Url

interface WeatherRepository
{
    // Remote
    suspend fun getCurrentWeather (@Url endUrl: String) : Resource<CurrentWeather>
    suspend fun getForecastWeatherResponse (@Url endUrl: String) : Response<ForecastWeather>

    // CurrentWeather
    suspend fun insertCurrentWeather (currentWeather : CurrentWeather) : Long
    suspend fun updateCurrentWeather (currentWeather: CurrentWeather) : Int
    suspend fun deleteCurrentWeather (currentWeather: CurrentWeather) : Int
    fun getAllCurrentWeather() : LiveData<List<CurrentWeather>>

    fun getTableSize() : LiveData<Int>

    // ForecastWeather
    suspend fun insertAllForecastWeather (listOfForecastWeather : List<ForecastWeather>)
    fun getAllForecastWeather() : LiveData<List<ForecastWeather>>

    fun observeCurrentWeather(currentWeather: CurrentWeather) : LiveData<Resource<CurrentWeather>>
}