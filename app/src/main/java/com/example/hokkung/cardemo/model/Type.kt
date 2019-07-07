package com.example.hokkung.cardemo.model

import androidx.annotation.DrawableRes

data class Type(
    val id: Int,
    val name: String,
    var isCheck: Boolean = false,
    @DrawableRes
    val image: Int
)