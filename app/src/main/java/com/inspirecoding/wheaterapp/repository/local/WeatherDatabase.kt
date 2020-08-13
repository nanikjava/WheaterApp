package com.inspirecoding.wheaterapp.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.inspirecoding.wheaterapp.model.CurrentWeather
import com.inspirecoding.wheaterapp.model.ForecastWeather
import com.inspirecoding.wheaterapp.util.ListOfListConverter
import com.inspirecoding.wheaterapp.util.ListOfWeatherConverter

@Database (
    entities = [CurrentWeather::class, ForecastWeather::class],
    version = 13)
@TypeConverters(ListOfWeatherConverter::class, ListOfListConverter::class)
abstract class WeatherDatabase : RoomDatabase()
{
    abstract fun currentWeatherDao() : CurrentWeatherDao
    abstract fun forecastWeatherDao() : ForecastWeatherDao

    companion object
    {
        @Volatile
        private var INSTANCE : WeatherDatabase? = null

        fun getDatabase (context: Context) : WeatherDatabase
        {
            val tempInstance = INSTANCE

            if(tempInstance != null)
            {
                return tempInstance
            }

            synchronized(WeatherDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance

                return instance
            }

        }
    }
}