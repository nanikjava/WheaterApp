package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Temp")
data class Temp (

    @PrimaryKey(autoGenerate = true)
    var tempId: Int? = 0,


    @SerializedName("morn")
    var morn: Double? = null,
    @SerializedName("day")
    var day: Double? = null,
    @SerializedName("eve")
    var eve: Double? = null,
    @SerializedName("night")
    var night: Double? = null,
    @SerializedName("min")
    var min: Double? = null,
    @SerializedName("max")
    var max: Double? = null
)