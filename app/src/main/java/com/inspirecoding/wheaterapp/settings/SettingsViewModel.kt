package com.inspirecoding.wheaterapp.settings

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

class SettingsViewModel @ViewModelInject constructor (
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


    fun setDarkMode(darkMode : Boolean)
    {
        SettingsValues.setDarkMode(darkMode = darkMode)

        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferencesRepository.setDarkMode(darkMode = darkMode)
        }
    }
    fun setUnit(unit : String)
    {
        SettingsValues.setUnit(unit = unit)

        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferencesRepository.setUnit(unit)
        }
    }
}