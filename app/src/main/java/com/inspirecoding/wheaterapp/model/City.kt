package com.inspirecoding.wheaterapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "City")
data class City(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,


    @SerializedName("name")
    var name: String = "",

    @SerializedName("coord")
    @Embedded
    val coord: Coord = Coord(),

    @SerializedName("country")
    val country: String? = null
)