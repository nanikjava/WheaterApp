package com.inspirecoding.wheaterapp.repository.sharedPreferences

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
): SharedPreferencesRepository
{
    override val KEY_UNIT = "unit"
    override val KEY_DARK_MODE = "dark_mode"

    private lateinit var mySharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun initFilterSharedPreferences(sharedPreferences: SharedPreferences)
    {
        mySharedPreferences = sharedPreferences
    }

    /** Unit **/
    override suspend fun setUnit(unit: String)
    {
        editor = mySharedPreferences.edit()

        editor.putString(KEY_UNIT, unit)

        editor.apply()
    }
    override suspend fun getUnit(): String?
    {
        return mySharedPreferences.getString(KEY_UNIT, "")
    }

    /** Dark mode **/
    override suspend fun setDarkMode(darkMode: Boolean)
    {
        editor = mySharedPreferences.edit()

        editor.putBoolean(KEY_DARK_MODE, darkMode)

        editor.apply()
    }
    override suspend fun getDarkMode(): Boolean?
    {
        return mySharedPreferences.getBoolean(KEY_DARK_MODE, false)
    }
}