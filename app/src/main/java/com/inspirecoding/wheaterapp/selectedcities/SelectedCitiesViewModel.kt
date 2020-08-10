package com.inspirecoding.wheaterapp.selectedcities

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.inspirecoding.wheaterapp.repository.WeatherRepository

class SelectedCitiesViewModel @ViewModelInject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    val allSelectedCities = weatherRepository.getAllCurrentWeather()
}