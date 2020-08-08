package com.inspirecoding.wheaterapp.model

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.inspirecoding.wheaterapp.util.ListOfWeatherConverter
import java.math.BigInteger
import kotlin.collections.List

@Entity(tableName = "CurrentWeather")
data class CurrentWeather(
//
//    @PrimaryKey(autoGenerate = true)
//    var currentWeatherId: Int = 1,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    @NonNull
    @PrimaryKey
    val name: String = "",


    @SerializedName("coord")
    @Embedded
    var coord: Coord = Coord(),

    @SerializedName("weather")
    @TypeConverters(ListOfWeatherConverter::class)
    val weather: List<Weather>? = null,

    @SerializedName("base")
    @ColumnInfo(name = "base")
    val base: String? = null,

    @SerializedName("main")
    @Embedded
    val main: Main = Main(),

    @SerializedName("wind")
    @Embedded
    val wind: Wind = Wind(),

    @SerializedName("rain")
    @Embedded
    val rain: Rain = Rain(),

    @SerializedName("clouds")
    @Embedded
    val clouds: Clouds = Clouds(),

    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Long? = null,

    @SerializedName("sys")
    @Embedded
    val sys: Sys = Sys(),

    @SerializedName("id")
    @ColumnInfo(name = "id")
    var cityId: Int? = null,

    @SerializedName("cod")
    @ColumnInfo(name = "cod")
    val cod: Int? = null
)