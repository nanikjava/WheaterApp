package com.inspirecoding.wheaterapp.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.model.Resource
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import com.inspirecoding.wheaterapp.util.combineWith

class WeatherViewModel @ViewModelInject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    fun listOfCurrentWeather() = weatherRepository.getAllCurrentWeather()

    fun getWeatherObservable(currentWeather: CurrentWeather) : LiveData<Pair<Resource<CurrentWeather>?, Resource<ForecastWeather>?>>
    {
        return weatherRepository.observeCurrentWeather(currentWeather)
            .combineWith(weatherRepository.observeForecastWeather(currentWeather.name))  { resultCurrentWeather, resultForecastWeather ->
            Pair(resultCurrentWeather, resultForecastWeather)
        }
    }

    fun getCurrentWeather(currentWeather: CurrentWeather) = weatherRepository.observeCurrentWeather(currentWeather)
    fun getForecastWeather(cityName: String) = weatherRepository.observeForecastWeather(cityName)

    fun getForecastWeatherLocal(cityName: String) = weatherRepository.getForecastWeatherLocal(cityName)
}