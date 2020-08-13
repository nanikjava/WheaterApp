package com.inspirecoding.wheaterapp.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.repository.WeatherRepository

class WeatherViewModel @ViewModelInject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    fun listOfCurrentWeather() = weatherRepository.getAllCurrentWeather()

    fun getCurrentWeather(currentWeather: CurrentWeather) = weatherRepository.observeCurrentWeather(currentWeather)
    fun getForecastWeather(forecastWeather: ForecastWeather) = weatherRepository.observeForecastWeather(forecastWeather)

    fun getForecastWeatherLocal(cityName: String) = weatherRepository.getForecastWeatherLocal(cityName)
}