package com.inspirecoding.wheaterapp.repository

import androidx.lifecycle.LiveData
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.Resource
import com.inspirecoding.wheaterapp.model.State
import kotlinx.coroutines.flow.Flow
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
    suspend fun getAllCurrentWeather() : List<CurrentWeather>
    suspend fun getFirstCityWeatherSuspend() : Flow<State<CurrentWeather>>
    suspend fun getCurrentWeatherTableSize() : Int

    // ForecastWeather
    suspend fun insertForecastWeather(forecastWeather: ForecastWeather) : Long
    suspend fun updateForecastWeather (forecastWeather: ForecastWeather) : Int
    suspend fun deleteForecastWeather (forecastWeather: ForecastWeather) : Int
    fun getForecastWeatherLocal(cityName: String) : LiveData<ForecastWeather>
    suspend fun getAllForecastWeather() : List<ForecastWeather>
    fun getForecastWeatherTableSize() : LiveData<Int>

    // Observer functions
    fun observeWeather(currentWeather: CurrentWeather) : LiveData<Resource<Pair<CurrentWeather?, ForecastWeather?>>>
}