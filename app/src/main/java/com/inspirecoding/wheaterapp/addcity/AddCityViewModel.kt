package com.inspirecoding.wheaterapp.addcity

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.combineWith
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*

class AddCityViewModel @ViewModelInject constructor (
    @ApplicationContext private val  applicationContext: Context,
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    var foundCurrentWeatherOfCity: CurrentWeather? = null
    var foundForecastWeatherOfCity: ForecastWeather? = null

    private val _hasCityFound = MutableLiveData<Boolean>(false)
    val hasCityFound: LiveData<Boolean> = _hasCityFound
    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> = _resultText

    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>  = _toast
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading


    fun getWeather(city: String)
    {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try
            {
                val currentWeatherEndUrl = Common.createEndUrl_currentWeather(city, "metric")
                val forecastWeatherEndUrl = Common.createEndUrl_forecastWeather(city, "metric")
                val deferreds = listOf(
                    async { weatherRepository.getCurrentWeather(currentWeatherEndUrl) },
                    async { weatherRepository.getForecastWeatherRemote(forecastWeatherEndUrl) }
                )

                deferreds.awaitAll().apply {
                    foundCurrentWeatherOfCity = if(this[0].data != null) {
                        this[0].data as CurrentWeather
                    } else {
                        null
                    }
                    foundForecastWeatherOfCity = if(this[1].data != null) {
                        this[1].data as ForecastWeather
                    } else {
                        null
                    }

                    if (foundCurrentWeatherOfCity == null)
                    {
                        _hasCityFound.postValue(false)
                        _resultText.postValue(applicationContext.getString(R.string.no_city_found))
                        _city.postValue(null)
                    }
                    else
                    {
                        _hasCityFound.postValue(true)
                        _resultText.postValue(applicationContext.getString(R.string.result))
                        _city.postValue("${(foundCurrentWeatherOfCity as CurrentWeather).name}, ${(foundCurrentWeatherOfCity as CurrentWeather).sys.country}")
                    }
                    _isLoading.postValue(false)
                }
            }
            catch (exception: Exception)
            {
                _toast.postValue(exception.message)
                _isLoading.postValue(false)
            }
        }
    }

    fun insertWeather() : LiveData<Pair<Long?, Long?>>
    {
        return insertCurrentWeather(foundCurrentWeatherOfCity!!)
            .combineWith(insertForecastWeather(foundForecastWeatherOfCity!!))  { resultCurrentWeather, resultForecastWeather ->
                Pair(resultCurrentWeather, resultForecastWeather)
            }
    }

    fun getCurrentWeatherTableSize() : LiveData<Int>
    {
        val size = MutableLiveData<Int>()
        viewModelScope.launch {
            size.value = weatherRepository.getCurrentWeatherTableSize()
        }
        return size
    }
    fun insertCurrentWeather(currentWeather : CurrentWeather) = liveData(Dispatchers.IO) {
            emit(weatherRepository.insertCurrentWeather(currentWeather))
    }

    fun getForecastWeatherTableSize() = weatherRepository.getForecastWeatherTableSize()
    fun insertForecastWeather(forecastWeather : ForecastWeather) = liveData(Dispatchers.IO) {
            emit(weatherRepository.insertForecastWeather(forecastWeather))
    }
}