package com.inspirecoding.wheaterapp.util.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.inspirecoding.wheaterapp.model.Minutely
import java.lang.reflect.Type

class ListOfMinutelyConverter
{
    companion object
    {
        @TypeConverter
        @JvmStatic
        fun stringToObjectList(data: String?): List<Minutely>?
        {
            if (data == null)
            {
                return null
            }

            val gson = Gson()

            val listType: Type = object : TypeToken<List<Minutely>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<Minutely>?): String?
        {
            val gson = Gson()

            return gson.toJson(someObjects)
        }
    }
}