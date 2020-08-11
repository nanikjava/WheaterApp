package com.inspirecoding.wheaterapp.addcity

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.inspirecoding.wheaterapp.R
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.Resource
import com.inspirecoding.wheaterapp.repository.WeatherRepository
import com.inspirecoding.wheaterapp.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers

class AddCityViewModel @ViewModelInject constructor (
    @ApplicationContext private val  applicationContext: Context,
    private val weatherRepository: WeatherRepository
) : ViewModel()
{
    var foundCurrentWeatherOfCity: CurrentWeather? = null

    private val _hasCityFound = MutableLiveData<Boolean>(false)
    val hasCityFound: LiveData<Boolean> = _hasCityFound
    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> = _resultText

    fun getCurrentWeather(city: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try
        {
            val endUrl = Constants.createEndUrl(city)
//            foundCurrentWeatherOfCity = weatherRepository.getCurrentWeather(endUrl).data
//            if (foundCurrentWeatherOfCity == null)
//            {
//                _hasCityFound.postValue(false)
//                _resultText.postValue(applicationContext.getString(R.string.no_city_found))
//            }
//            else
//            {
//                _hasCityFound.postValue(true)
//                _resultText.postValue(applicationContext.getString(R.string.result))
//            }
//            emit(Resource.success(data = foundCurrentWeatherOfCity))
        }
        catch (exception: Exception)
        {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: applicationContext.getString(R.string.error_occurred)
                ))
        }
    }
    fun getCurrentWeatherTableSize() = weatherRepository.getTableSize()
    fun insertCity(currentWeather : CurrentWeather) = liveData(Dispatchers.IO) {
            emit(weatherRepository.insertCurrentWeather(currentWeather))
    }
}