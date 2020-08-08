package com.inspirecoding.wheaterapp.repository.remote

import com.inspirecoding.wheaterapp.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherServiceAPI
{
    @GET
    suspend fun getCurrentWeatherResponse (
        @Url endUrl: String
    ) : Response<CurrentWeather>
}