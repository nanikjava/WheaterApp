package com.inspirecoding.wheaterapp.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.Resource
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import com.inspirecoding.wheaterapp.util.combineWith
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel @ViewModelInject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    fun listOfWeather() : LiveData<MutableList<Pair<CurrentWeather, ForecastWeather>>>
    {
        val list = MutableLiveData<MutableList<Pair<CurrentWeather, ForecastWeather>>>()
        viewModelScope.launch {
            val currentWeatherResponse = async { weatherRepository.getAllCurrentWeather() }
            val forecastWeatherResponse = async { weatherRepository.getAllForecastWeather() }

            val listOfCurrentWeather = currentWeatherResponse.await()
            val listOfForecastWeather = forecastWeatherResponse.await()

            val pair = getCoupledList(listOfCurrentWeather, listOfForecastWeather)
            list.postValue(pair)
        }
        return list
    }

    private fun getCoupledList(listOfCurrentWeather: List<CurrentWeather>, listOfForecastWeather: List<ForecastWeather>) : MutableList<Pair<CurrentWeather, ForecastWeather>>
    {
        val pair : MutableList<Pair<CurrentWeather, ForecastWeather>> = mutableListOf()

        for (currentWeather in listOfCurrentWeather)
        {
            val forecastWeather = listOfForecastWeather.find {
                it.cityName == currentWeather.name
            }
            forecastWeather?.let {
                pair.add(Pair(currentWeather, forecastWeather))
            }
        }

        return pair
    }
    fun observeWeather(currentWeather: CurrentWeather) = weatherRepository.observeWeather(currentWeather)
}