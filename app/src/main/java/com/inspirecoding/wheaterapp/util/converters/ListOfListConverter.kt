package com.inspirecoding.wheaterapp.util.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListOfListConverter
{
    companion object
    {
        @TypeConverter
        @JvmStatic
        fun stringToObjectList(data: String?): List<com.inspirecoding.wheaterapp.model.List>?
        {
            if (data == null)
            {
                return null
            }

            val gson = Gson()

            val listType: Type = object : TypeToken<List<com.inspirecoding.wheaterapp.model.List>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<com.inspirecoding.wheaterapp.model.List>?): String?
        {
            val gson = Gson()

            return gson.toJson(someObjects)
        }
    }
}