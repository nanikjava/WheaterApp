package com.inspirecoding.wheaterapp.repository

import androidx.lifecycle.LiveData
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.repository.local.CurrentWeatherDao
import com.inspirecoding.wheaterapp.repository.local.ForecastWeatherDao
import com.inspirecoding.wheaterapp.repository.remote.BaseDataSource
import com.inspirecoding.wheaterapp.repository.remote.WeatherServiceAPI
import com.inspirecoding.wheaterapp.util.Constants
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (
    private val weatherServiceAPI: WeatherServiceAPI,
    private val currentWeatherDao: CurrentWeatherDao,
    private val forecastWeatherDao: ForecastWeatherDao
) : WeatherRepository, BaseDataSource()
{
    override suspend fun getCurrentWeatherResponse(endUrl: String): CurrentWeather
    {
        TODO("Not yet implemented")
    }

    override suspend fun insertAllCurrentWeather(listOfWeather: List<CurrentWeather>)
    {
        TODO("Not yet implemented")
    }

    override fun getAllCurrentWeather(): LiveData<List<CurrentWeather>>
    {
        TODO("Not yet implemented")
    }

    override suspend fun insertAllForecastWeather(listOfForecastWeather: List<ForecastWeather>)
    {
        TODO("Not yet implemented")
    }

    override fun getAllForecastWeather(): LiveData<List<ForecastWeather>>
    {
        TODO("Not yet implemented")
    }

    override fun observeListOfCurrentWeather() = resultLiveData(
        // Get list of articles from Room
        databaseQuery = {
            currentWeatherDao.getAllCurrentWeather()
        },
        // Refresh the list of articles from network
        networkCall = {
            getResult {
                val weatherUrl =   java.lang.String.format (
                    "weather?q=%s&units=%s&appid=%s",
                    "Las Vegas", "metric", Constants.API_KEY
                )
                weatherServiceAPI.getCurrentWeatherResponse(weatherUrl)
            }
        },
        // If the network call was successful then update the list in Room
        saveCallResult = {
            it.let { _currentWeather ->
                currentWeatherDao.insertAllCurrentWeather(_currentWeather)
            }
        }
    )

    override suspend fun getForecastWeatherResponse(endUrl: String): Response<ForecastWeather>
    {
        TODO("Not yet implemented")
    }
}