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
    val listOfCurrentWeather: LiveData<List<CurrentWeather>> = weatherRepository.getAllCurrentWeather()

    fun getCurrentWeather(currentWeather: CurrentWeather) = weatherRepository.observeCurrentWeather(currentWeather)
}