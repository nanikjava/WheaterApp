package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Weather")
data class Weather(

    @PrimaryKey(autoGenerate = true)
    var weatherId: Int? = 0,


    @SerializedName("id")
    var weatherConditionId: Int = 0,

    @SerializedName("main")
    var main: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("icon")
    val icon: String? = null

)