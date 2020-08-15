package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "FeelsLike")
data class FeelsLike (

    @PrimaryKey(autoGenerate = true)
    var feelsLikeId: Int? = 0,



    @SerializedName("day")
    var day: Double? = null,
    @SerializedName("night")
    var night: Double? = null,
    @SerializedName("eve")
    var eve: Double? = null,
    @SerializedName("morn")
    var morn: Double? = null
)