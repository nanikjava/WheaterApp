package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Sys")
data class Sys(

    @PrimaryKey(autoGenerate = true)
    var sysId: Int? = 0,

    @SerializedName("message")
    var message: Double? = null,

    @SerializedName("country")
    val country: String? = null,

    @SerializedName("sunrise")
    val sunrise: Int = 0,

    @SerializedName("sunset")
    val sunset: Int = 0
)