package com.inspirecoding.wheaterapp.util

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
        fun fromWeatherList(weather: List<Weather?>?): String? {
            if (weather == null) {
                return null
            }
            val gson = Gson()
            val type: Type =
                object : TypeToken<List<Weather?>?>() {}.type
            return gson.toJson(weather, type)
        }

        @TypeConverter
        @JvmStatic
        fun toWeatherList(weatherString: String?): List<Weather>? {
            if (weatherString == null) {
                return null
            }
            val gson = Gson()
            val type: Type =
                object : TypeToken<List<Weather?>?>() {}.type
            return gson.fromJson<List<Weather>>(weatherString, type)
        }




//        var gson = Gson()
//
//        @TypeConverter
//        @JvmStatic
//        fun toWeatherList(data: String?): List<Weather>?
//        {
//            if (data == null)
//            {
//                return Collections.emptyList()
//            }
//
//            val listType = object : TypeToken<List<String>>() {}.type
//            return gson.fromJson(data, listType)
//        }
//
//        @TypeConverter
//        @JvmStatic
//        fun someObjectListToString(someObjects: List<Weather>?): String?
//        {
//            return gson.toJson(someObjects)
//        }
    }
}