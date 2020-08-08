package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity (tableName = "Main")
data class Main (

    @PrimaryKey(autoGenerate = true)
    var mainId: Int? = 0,

    @SerializedName("temp")
    var temp: Double? = null,

    @SerializedName("pressure")
    val pressure: Double? = null,

    @SerializedName("humidity")
    val humidity: Int? = null,

    @SerializedName("temp_min")
    val tempMin: Double? = null,

    @SerializedName("temp_max")
    val tempMax: Double? = null,

    @SerializedName("sea_level")
    val seaLevel: Double? = null,

    @SerializedName("grnd_level")
    val grndLevel: Double? = null
)