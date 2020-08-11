package com.inspirecoding.wheaterapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.Resource
import com.inspirecoding.wheaterapp.repository.local.CurrentWeatherDao
import com.inspirecoding.wheaterapp.repository.local.ForecastWeatherDao
import com.inspirecoding.wheaterapp.repository.remote.BaseDataSource
import com.inspirecoding.wheaterapp.repository.remote.WeatherServiceAPI
import com.inspirecoding.wheaterapp.util.Constants
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (
    private val weatherServiceAPI: WeatherServiceAPI,
    private val currentWeatherDao: CurrentWeatherDao,
    private val forecastWeatherDao: ForecastWeatherDao
) : WeatherRepository, BaseDataSource()
{
    override suspend fun getCurrentWeather(endUrl: String) : Resource<CurrentWeather>
    {
        return getResult {
            weatherServiceAPI.getCurrentWeather(endUrl)
        }
    }

    override fun getTableSize() = currentWeatherDao.getTableSize()

    override suspend fun insertCurrentWeather(currentWeather: CurrentWeather) = currentWeatherDao.insertCurrentWeather(currentWeather)

    override fun getAllCurrentWeather(): LiveData<List<CurrentWeather>> = currentWeatherDao.getAllCurrentWeather()

    override suspend fun updateCurrentWeather(currentWeather: CurrentWeather) : Int
    {
        return currentWeatherDao.updateCurrentWeather(currentWeather)
    }

    override suspend fun insertAllForecastWeather(listOfForecastWeather: List<ForecastWeather>)
    {
        TODO("Not yet implemented")
    }

    override fun getAllForecastWeather(): LiveData<List<ForecastWeather>>
    {
        TODO("Not yet implemented")
    }

    override fun observeCurrentWeather(currentWeather: CurrentWeather) = resultLiveData(
        // Get list of articles from Room
        databaseQuery = {
            currentWeatherDao.getCurrentWeather(currentWeather.name)
        },
        // Refresh the list of articles from network
        networkCall = {
            getResult {
                val weatherUrl =   java.lang.String.format (
                    "weather?q=%s&units=%s&appid=%s",
                    currentWeather.name, "metric", Constants.API_KEY
                )
                weatherServiceAPI.getCurrentWeather(weatherUrl)
            }
        },
        // If the network call was successful then update the list in Room
        saveCallResult = {
            it.let { _currentWeather ->
                _currentWeather.position = currentWeather.position
                currentWeatherDao.insertCurrentWeather(_currentWeather)
            }
        }
    )

    override suspend fun getForecastWeatherResponse(endUrl: String): Response<ForecastWeather>
    {
        TODO("Not yet implemented")
    }
}