package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "Coord")
data class Coord (

    @PrimaryKey(autoGenerate = true)
    var coordId: Int? = 0,

    @SerializedName ("lon")
    var longitude : Double? = null,
    @SerializedName ("lat")
    var latitude : Double? = null
)