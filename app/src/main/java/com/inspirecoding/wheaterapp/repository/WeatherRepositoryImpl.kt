package com.inspirecoding.wheaterapp.repository

import androidx.lifecycle.LiveData
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.Resource
import com.inspirecoding.wheaterapp.repository.local.CurrentWeatherDao
import com.inspirecoding.wheaterapp.repository.local.ForecastWeatherDao
import com.inspirecoding.wheaterapp.repository.remote.BaseDataSource
import com.inspirecoding.wheaterapp.repository.remote.WeatherServiceAPI
import com.inspirecoding.wheaterapp.util.Common
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (
    private val weatherServiceAPI: WeatherServiceAPI,
    private val currentWeatherDao: CurrentWeatherDao,
    private val forecastWeatherDao: ForecastWeatherDao
) : WeatherRepository, BaseDataSource()
{
    // Remote
    override suspend fun getCurrentWeather(endUrl: String) : Resource<CurrentWeather>
    {
        return getResult {
            weatherServiceAPI.getCurrentWeather(endUrl)
        }
    }
    override suspend fun getForecastWeatherRemote(endUrl: String): Resource<ForecastWeather>
    {
        return getResult {
            weatherServiceAPI.getForecastWeather(endUrl)
        }
    }

    // CurrentWeather
    override suspend fun insertCurrentWeather(currentWeather: CurrentWeather) = currentWeatherDao.insertCurrentWeather(currentWeather)
    override suspend fun updateCurrentWeather(currentWeather: CurrentWeather) = currentWeatherDao.updateCurrentWeather(currentWeather)
    override suspend fun deleteCurrentWeather (currentWeather: CurrentWeather) = currentWeatherDao.deleteCurrentWeather(currentWeather)
    override fun getAllCurrentWeather(): LiveData<List<CurrentWeather>> = currentWeatherDao.getAllCurrentWeather()
    override fun getCurrentWeatherTableSize() = currentWeatherDao.getTableSize()

    // ForecastWeather
    override suspend fun insertForecastWeather(forecastWeather: ForecastWeather) = forecastWeatherDao.insertForecastWeather(forecastWeather)
    override suspend fun updateForecastWeather(forecastWeather: ForecastWeather) = forecastWeatherDao.updateForecastWeather(forecastWeather)
    override suspend fun deleteForecastWeather(forecastWeather: ForecastWeather) = forecastWeatherDao.deleteForecastWeather(forecastWeather)
    override fun getForecastWeatherLocal(cityName: String) = forecastWeatherDao.getForecastWeather(cityName)
    override fun getAllForecastWeather(): LiveData<List<ForecastWeather>> = forecastWeatherDao.getAllForecastWeather()
    override fun getForecastWeatherTableSize() = forecastWeatherDao.getTableSize()

    // Observer functions
    override fun observeCurrentWeather(currentWeather: CurrentWeather) = resultLiveData(
        // Get list of articles from Room
        databaseQuery = {
            currentWeatherDao.getCurrentWeather(currentWeather.name)
        },
        // Refresh the list of articles from network
        networkCall = {
            getResult {
                val weatherUrl = Common.createEndUrl_currentWeather(currentWeather.name, "metric")
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
    override fun observeForecastWeather(cityName: String)= resultLiveData(
        // Get list of articles from Room
        databaseQuery = {
            forecastWeatherDao.getForecastWeather(cityName)
        },
        // Refresh the list of articles from network
        networkCall = {
            getResult {
                val weatherUrl = Common.createEndUrl_currentWeather(cityName, "metric")
                weatherServiceAPI.getForecastWeather(weatherUrl)
            }
        },
        // If the network call was successful then update the list in Room
        saveCallResult = {
            it.let { _forecastWeather ->
                forecastWeatherDao.insertForecastWeather(_forecastWeather)
            }
        }
    )
}