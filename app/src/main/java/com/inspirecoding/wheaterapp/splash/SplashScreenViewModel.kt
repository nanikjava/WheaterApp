package com.inspirecoding.wheaterapp.splash

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirecoding.wheaterapp.repository.sharedPreferences.SharedPreferencesRepository
import com.inspirecoding.wheaterapp.util.Common
import com.inspirecoding.wheaterapp.util.SettingsValues
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashScreenViewModel @ViewModelInject constructor (
    @ApplicationContext private val  applicationContext: Context,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel()
{
    init
    {
        sharedPreferencesRepository.initFilterSharedPreferences(
            applicationContext.getSharedPreferences(
                Common.SHARED_PREFERENCES, Context.MODE_PRIVATE
            )
        )
    }


    fun getSharedPreferences()
    {
        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferencesRepository.getUnit()?.let { _unit ->
                SettingsValues.setUnit(_unit)
            }
            sharedPreferencesRepository.getDarkMode()?.let { _darkMode ->
                SettingsValues.setDarkMode(_darkMode)
            }
        }
    }
}