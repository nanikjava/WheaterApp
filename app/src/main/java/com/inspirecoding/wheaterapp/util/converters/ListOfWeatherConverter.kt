package com.inspirecoding.wheaterapp.util.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inspirecoding.wheaterapp.model.Weather
import java.lang.reflect.Type

class ListOfWeatherConverter
{
    companion object
    {
        @TypeConverter
        @JvmStatic
        fun toWeatherList(weatherString: String?): List<Weather>?
        {
            if (weatherString == null)
            {
                return null
            }
            val gson = Gson()

            val type: Type = object : TypeToken<List<Weather?>?>() {}.type
            return gson.fromJson<List<Weather>>(weatherString, type)
        }

        @TypeConverter
        @JvmStatic
        fun fromWeatherList(weather: List<Weather?>?): String?
        {
            if (weather == null)
            {
                return null
            }
            val gson = Gson()
            val type: Type = object : TypeToken<List<Weather?>?>() {}.type
            return gson.toJson(weather, type)
        }
    }
}