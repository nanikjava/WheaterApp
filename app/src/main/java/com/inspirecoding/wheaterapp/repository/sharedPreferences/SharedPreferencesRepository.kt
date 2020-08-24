package com.inspirecoding.wheaterapp.repository.sharedPreferences

import android.content.SharedPreferences
import javax.inject.Inject

interface SharedPreferencesRepository
{
    val KEY_UNIT: String
    val KEY_DARK_MODE: String

    fun initFilterSharedPreferences(sharedPreferences: SharedPreferences)

    /** Unit **/
    suspend fun setUnit(unit: String)
    suspend fun getUnit(): String?

    /** Dark mode **/
    suspend fun setDarkMode(darkMode: Boolean)
    suspend fun getDarkMode(): Boolean?
}