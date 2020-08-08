package com.inspirecoding.wheaterapp.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.repository.WeatherRepository

class WeatherViewModel @ViewModelInject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    val currentWeatherLiveData by lazy {
        weatherRepository.observeListOfCurrentWeather()
    }

    private val _listOfCurrentWeather = MutableLiveData<List<CurrentWeather>>()
    val listOfCurrentWeather: LiveData<List<CurrentWeather>> = _listOfCurrentWeather
}