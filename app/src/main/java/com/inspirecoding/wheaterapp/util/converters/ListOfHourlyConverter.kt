package com.inspirecoding.wheaterapp.util.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inspirecoding.wheaterapp.model.Hourly
import java.lang.reflect.Type

class ListOfHourlyConverter
{
    companion object
    {
        @TypeConverter
        @JvmStatic
        fun stringToObjectList(data: String?): List<Hourly>?
        {
            if (data == null)
            {
                return null
            }

            val gson = Gson()

            val listType: Type = object : TypeToken<List<Hourly>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<Hourly>?): String?
        {
            val gson = Gson()

            return gson.toJson(someObjects)
        }
    }
}
