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
    suspend fun getForecastWeatherRemote (@Url endUrl: String) : Resource<ForecastWeather>

    // CurrentWeather
    suspend fun insertCurrentWeather (currentWeather : CurrentWeather) : Long
    suspend fun updateCurrentWeather (currentWeather: CurrentWeather) : Int
    suspend fun deleteCurrentWeather (currentWeather: CurrentWeather) : Int
    fun getAllCurrentWeather() : LiveData<List<CurrentWeather>>
    fun getCurrentWeatherTableSize() : LiveData<Int>

    // ForecastWeather
    suspend fun insertForecastWeather(forecastWeather: ForecastWeather) : Long
    suspend fun updateForecastWeather (forecastWeather: ForecastWeather) : Int
    suspend fun deleteForecastWeather (forecastWeather: ForecastWeather) : Int
    fun getForecastWeatherLocal(cityName: String) : LiveData<ForecastWeather>
    fun getAllForecastWeather() : LiveData<List<ForecastWeather>>
    fun getForecastWeatherTableSize() : LiveData<Int>

    // Observer functions
    fun observeCurrentWeather(currentWeather: CurrentWeather) : LiveData<Resource<CurrentWeather>>
    fun observeForecastWeather(cityName: String) : LiveData<Resource<ForecastWeather>>
    fun observeWeather(currentWeather: CurrentWeather) : LiveData<Resource<Pair<CurrentWeather?, ForecastWeather?>>>
}