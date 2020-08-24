package com.inspirecoding.wheaterapp.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object SettingsValues
{
    private val _unit = MutableLiveData<String>("metric")
    var unit : LiveData<String> = _unit

    private val _darkMode = MutableLiveData<Boolean>(false)
    var darkMode : LiveData<Boolean> = _darkMode

    fun setUnit(unit: String)
    {
        _unit.postValue(unit)
    }
    fun setDarkMode(darkMode: Boolean)
    {
        _darkMode.postValue(darkMode)

        when (darkMode)
        {
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}