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
import com.inspirecoding.wheaterapp.util.combineWith
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject
import kotlin.system.measureTimeMillis

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
                _forecastWeather.cityName = cityName
                forecastWeatherDao.insertForecastWeather(_forecastWeather)
            }
        }
    )
    override fun observeWeather(currentWeather: CurrentWeather)= resultLiveData(
        // Get list of articles from Room
        databaseQuery = {
            getWeather(currentWeather.name)
        },
        // Refresh the list of articles from network
        networkCall = {
            getResult {
                runBlocking {
                    val currentWeatherEndUrl = Common.createEndUrl_currentWeather(currentWeather.name, "metric")
                    val forecastWeatherEndUrl = Common.createEndUrl_forecastWeather(currentWeather.name, "metric")
                    val currentWeatherResponse = async { weatherServiceAPI.getCurrentWeather(currentWeatherEndUrl) }
                    val forecastWeatherResponse = async { weatherServiceAPI.getForecastWeather(forecastWeatherEndUrl) }
                    Response.success(Pair(currentWeatherResponse.await().body(), forecastWeatherResponse.await().body()))
                }
            }
        },
        // If the network call was successful then update the list in Room
        saveCallResult = {
//            it.let { result ->
//                val _currentWeather = result.first
//                val _forecastWeather = result.second
//                _currentWeather?.let {
//                    _currentWeather.position = currentWeather.position
//                    currentWeatherDao.insertCurrentWeather(_currentWeather)
//                }
//                _forecastWeather?.let {
//                    _forecastWeather.cityName = currentWeather.name
//                    forecastWeatherDao.insertForecastWeather(_forecastWeather)
//                }
//            }
        }
    )
    fun getWeather(cityName: String) : LiveData<Pair<CurrentWeather?, ForecastWeather?>>
    {
        return currentWeatherDao.getCurrentWeather(cityName).combineWith(forecastWeatherDao.getForecastWeather(cityName)) {  resultCurrentWeather, resultForecastWeather ->
            Pair(resultCurrentWeather, resultForecastWeather)
        }
    }
}