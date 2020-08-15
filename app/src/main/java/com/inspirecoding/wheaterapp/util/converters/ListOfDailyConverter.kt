package com.inspirecoding.wheaterapp.util.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inspirecoding.wheaterapp.model.Daily
import java.lang.reflect.Type

class ListOfDailyConverter
{
    companion object
    {
        @TypeConverter
        @JvmStatic
        fun stringToObjectList(data: String?): List<Daily>?
        {
            if (data == null)
            {
                return null
            }

            val gson = Gson()

            val listType: Type = object : TypeToken<List<Daily>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<Daily>?): String?
        {
            val gson = Gson()

            return gson.toJson(someObjects)
        }
    }
}
