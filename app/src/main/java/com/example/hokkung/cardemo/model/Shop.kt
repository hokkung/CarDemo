package com.example.hokkung.cardemo.model

import androidx.room.Ignore
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Shop (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)