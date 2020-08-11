package com.inspirecoding.wheaterapp.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import com.inspirecoding.wheaterapp.util.Constants
import com.inspirecoding.wheaterapp.util.Status
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class WeatherViewModel @ViewModelInject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    fun listOfCurrentWeather(): LiveData<List<CurrentWeather>>
    {
        val result = weatherRepository.getAllCurrentWeather()

        return result
    }

    fun getCurrentWeather(currentWeather: CurrentWeather) = weatherRepository.observeCurrentWeather(currentWeather)
}