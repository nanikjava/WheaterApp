package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FeelsLike")
data class FeelsLike(

    @PrimaryKey(autoGenerate = true)
    var feelsLikeId: Int? = 0,


    @SerializedName("morn")
    var morn: Double? = null,
    @SerializedName("day")
    var day: Double? = null,
    @SerializedName("eve")
    var eve: Double? = null,
    @SerializedName("night")
    var night: Double? = null
)