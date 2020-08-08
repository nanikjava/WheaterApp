package com.inspirecoding.wheaterapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inspirecoding.wheaterapp.model.Weather
import java.util.*

class ListOfListConverter
{
    companion object
    {
        var gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromTimestamp(data: String?): List<com.inspirecoding.wheaterapp.model.List>?
        {
            if (data == null)
            {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<com.inspirecoding.wheaterapp.model.List>?): String?
        {
            return gson.toJson(someObjects)
        }
    }
}