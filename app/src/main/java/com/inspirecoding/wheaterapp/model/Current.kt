package com.inspirecoding.wheaterapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.inspirecoding.wheaterapp.util.converters.ListOfWeatherConverter
import kotlin.collections.List

@Entity(tableName = "Current")
data class Current (
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey
    val currentId: Int = 0,


    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Long? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("sunset")
    val sunset: Int? = null,
    @SerializedName("temp")
    val temp: Double? = null,
    @SerializedName("feels_like")
    val feels_like: Double? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("dew_point")
    val dew_point: Double? = null,
    @SerializedName("uvi")
    val uvi: Double? = null,
    @SerializedName("clouds")
    val clouds: Int? = null,
    @SerializedName("visibility")
    val visibility: Int? = null,
    @SerializedName("wind_speed")
    val wind_speed: Double? = null,
    @SerializedName("wind_deg")
    val wind_deg: Int? = null,
    @SerializedName("weather")
    @TypeConverters(ListOfWeatherConverter::class)
    val weather: List<Weather>? = null
)