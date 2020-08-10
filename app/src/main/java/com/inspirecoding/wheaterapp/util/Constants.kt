package com.inspirecoding.wheaterapp.util

object Constants
{
    const val WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/"
    const val API_KEY = "754a696a08f9991eee2e73454a6bfdbe"

    fun createEndUrl(city: String) : String
    {
        return java.lang.String.format (
            "weather?q=%s&units=%s&appid=%s",
            city, "metric", API_KEY)
    }
}