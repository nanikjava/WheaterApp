package com.inspirecoding.wheaterapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Daily")
data class Daily(

    @PrimaryKey(autoGenerate = true)
    var dailyId: Int? = 0,


    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Long? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("sunset")
    val sunset: Int? = null,
    @SerializedName("temp")
    @Embedded
    val temp: Temp? = null,
    @SerializedName("feels_like")
    @Embedded
    val feels_like: FeelsLike? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("dew_point")
    val dew_point: Double? = null,
    @SerializedName("wind_speed")
    val wind_speed: Double? = null,
    @SerializedName("wind_deg")
    val wind_deg: Int? = null,
    @SerializedName("weather")
    @Embedded
    val weather: Weather? = null,
    @SerializedName("clouds")
    val clouds: Int? = null,
    @SerializedName("pop")
    val pop: Int? = null,
    @SerializedName("rain")
    val rain: Double? = null,
    @SerializedName("uvi")
    val uvi: Double? = null
)