package com.inspirecoding.wheaterapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Minutely")
data class Minutely (
    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey
    val id: Int = 0,


    @SerializedName("dt")
    @ColumnInfo(name = "dt")
    val dt: Long? = null,
    @SerializedName("precipitation")
    @ColumnInfo(name = "precipitation")
    val precipitation: Double? = null

)