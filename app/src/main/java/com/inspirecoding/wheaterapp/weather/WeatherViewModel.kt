package com.inspirecoding.wheaterapp.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.State
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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


    private val _currentWeather = MutableLiveData<State<Pair<CurrentWeather?, ForecastWeather?>>>()
    val currentWeather: LiveData<State<Pair<CurrentWeather?, ForecastWeather?>>> = _currentWeather
    fun getWeatherOfCity(currentWeather: CurrentWeather)
    {
        viewModelScope.launch(Dispatchers.IO){
            weatherRepository.getWeatherOfCity(currentWeather).collect {
//                Timber.d("collect: ${it}")
//                _currentWeather.postValue(it)
            }
        }
    }



}