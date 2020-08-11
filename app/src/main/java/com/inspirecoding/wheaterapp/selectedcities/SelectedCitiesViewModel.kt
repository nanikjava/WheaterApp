package com.inspirecoding.wheaterapp.selectedcities

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class SelectedCitiesViewModel @ViewModelInject constructor (
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    val allSelectedCities = weatherRepository.getAllCurrentWeather()
    var listOfSelectedCities = mutableListOf<CurrentWeather>()

    fun updateCurrentWeather (currentWeather: CurrentWeather)
    {
        viewModelScope.launch {
            weatherRepository.updateCurrentWeather(currentWeather)
        }
    }

    fun deleteCurrentWeather (from: Int)
    {
        viewModelScope.launch {
            weatherRepository.deleteCurrentWeather(listOfSelectedCities[from])
        }

        listOfSelectedCities.remove(listOfSelectedCities[from])

        setItemsPositionProperty()
    }

    fun moveItem(from: Int, to: Int)
    {
        val fromTodo = listOfSelectedCities[from]
        listOfSelectedCities.removeAt(from)

        if (to < from)
        {
            listOfSelectedCities.add(to, fromTodo)
        }
        else
        {
            listOfSelectedCities.add(to - 1, fromTodo)
        }
        setItemsPositionProperty()
    }

    private fun setItemsPositionProperty()
    {
        for (i in listOfSelectedCities.indices)
        {
            listOfSelectedCities[i].position = i
        }
    }
}