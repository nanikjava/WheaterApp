package com.inspirecoding.wheaterapp.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.inspirecoding.wheaterapp.util.ListOfListConverter
import com.inspirecoding.wheaterapp.util.ListOfWeatherConverter

@Entity(tableName = "ForecastWeather")
data class ForecastWeather (

    @PrimaryKey(autoGenerate = true)
    var forecastWeatherId: Int = 0,


    @SerializedName("cod")
    @ColumnInfo(name = "cod")
    var cod: String? = null,

    @SerializedName("message")
    @ColumnInfo(name = "message")
    val message: Double? = null,

    @SerializedName("cnt")
    @ColumnInfo(name = "cnt")
    val cnt: Int? = null,

    @SerializedName("list")
    @TypeConverters(ListOfListConverter::class)
    val list: kotlin.collections.List<List>? = null,

    @SerializedName("city")
    @Embedded
    val city: City = City()
)