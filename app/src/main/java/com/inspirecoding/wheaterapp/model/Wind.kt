package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Wind")
data class Wind(

    @PrimaryKey(autoGenerate = true)
    var windId: Int? = 0,

    @SerializedName("speed")
    var speed: Double? = null,

    @SerializedName("deg")
    val deg: Double? = null
)