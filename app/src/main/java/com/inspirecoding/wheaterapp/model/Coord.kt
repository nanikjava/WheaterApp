package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "Coord")
data class Coord (

    @PrimaryKey(autoGenerate = true)
    var coordId: Int? = 0,

    @SerializedName ("lon")
    var longitude : Double = 0.0,
    @SerializedName ("lat")
    var latitude : Double = 0.0
)