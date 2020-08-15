package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Snow")
data class Snow (

    @PrimaryKey(autoGenerate = true)
    var snowId: Int? = 0,
    @SerializedName("1h")
    var oneHour: Int = 0
)