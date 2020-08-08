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
    suspend fun getCurrentWeatherResponse (@Url endUrl: String) : CurrentWeather
    suspend fun getForecastWeatherResponse (@Url endUrl: String) : Response<ForecastWeather>

    // CurrentWeather
    suspend fun insertAllCurrentWeather (listOfWeather : List<CurrentWeather>)
    fun getAllCurrentWeather() : LiveData<List<CurrentWeather>>

    // ForecastWeatherDao
    suspend fun insertAllForecastWeather (listOfForecastWeather : List<ForecastWeather>)
    fun getAllForecastWeather() : LiveData<List<ForecastWeather>>

    fun observeListOfCurrentWeather() : LiveData<Resource<List<CurrentWeather>>>
}