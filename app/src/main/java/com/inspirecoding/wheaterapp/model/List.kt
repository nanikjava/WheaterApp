package com.inspirecoding.wheaterapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlin.collections.List

@Entity(tableName = "List")
data class List(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,


    @SerializedName("dt")
    var dt: Int = 0,

    @SerializedName("main")
    @Embedded
    val main: Main? = null,

    @SerializedName("weather")
    @Embedded
    val weather: List<Weather>? = null,

    @SerializedName("clouds")
    @Embedded
    val clouds: Clouds? = null,

    @SerializedName("wind")
    @Embedded
    val wind: Wind? = null,

    @SerializedName("sys")
    @Embedded
    val sys: Sys? = null,

    @SerializedName("dt_txt")
    val dtTxt: String? = null,

    @SerializedName("rain")
    @Embedded
    val rain: Rain? = null
)