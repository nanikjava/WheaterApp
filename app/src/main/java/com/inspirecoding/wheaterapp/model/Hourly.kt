package com.inspirecoding.wheaterapp.model

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.inspirecoding.wheaterapp.util.converters.ListOfWeatherConverter
import kotlin.collections.List

@Entity(tableName = "Hourly")
data class Hourly (
    @ColumnInfo(name = "hourlyId")
    @NonNull
    @PrimaryKey
    val hourlyId: Int = 0,

    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Long = 0,
    @SerializedName("temp")
    var temp: Double = 0.0,
    @SerializedName("feels_like")
    var feels_like: Double? = null,
    @SerializedName("pressure")
    val pressure: Double? = null,
    @SerializedName("humidity")
    val humidity: Double? = null,
    @SerializedName("dew_point")
    val dew_point: Double? = null,
    @SerializedName("clouds")
    val clouds: Double? = null,
    @SerializedName("visibility")
    val visibility: Double? = null,
    @SerializedName("wind_speed")
    val wind_speed: Double? = null,
    @SerializedName("wind_deg")
    val wind_deg: Double? = null,
    @SerializedName("weather")
    @TypeConverters(ListOfWeatherConverter::class)
    val weather: List<Weather>? = null,
    @SerializedName("pop")
    val pop: Double? = null
)