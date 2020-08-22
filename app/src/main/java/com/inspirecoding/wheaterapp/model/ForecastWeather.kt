package com.inspirecoding.wheaterapp.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.inspirecoding.wheaterapp.util.converters.ListOfDailyConverter
import com.inspirecoding.wheaterapp.util.converters.ListOfHourlyConverter
import com.inspirecoding.wheaterapp.util.converters.ListOfMinutelyConverter
import kotlin.collections.List

@Entity(tableName = "ForecastWeather")
data class ForecastWeather(

    @PrimaryKey
    @ColumnInfo(name = "cityName")
    var cityName: String = "",


    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("lon")
    var lon: Double? = null,
    @SerializedName("timezone")
    var timezone: String? = null,
    @SerializedName("timezone_offset")
    var timezone_offset: Int? = null,
    @SerializedName("current")
    @Embedded
    var current: Current = Current(),
    @SerializedName("minutely")
    @TypeConverters(ListOfMinutelyConverter::class)
    val minutely: List<Minutely>? = null,
    @SerializedName("hourly")
    @TypeConverters(ListOfHourlyConverter::class)
    val hourly: List<Hourly>? = null,
    @SerializedName("daily")
    @TypeConverters(ListOfDailyConverter::class)
    val daily: List<Daily>? = null
)