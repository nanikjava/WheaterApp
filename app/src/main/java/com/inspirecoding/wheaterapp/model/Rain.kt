package com.inspirecoding.wheaterapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity (tableName = "Rain")
data class Rain (

    @PrimaryKey(autoGenerate = true)
    var rainId: Int? = 0,

    @SerializedName("3h")
    var _3h: Double? = null
)