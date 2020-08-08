package com.inspirecoding.wheaterapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "Clouds")
data class Clouds (

    @PrimaryKey(autoGenerate = true)
    var cloudsId: Int? = 0,

    @SerializedName("all")
    var all: Int? = null
)
