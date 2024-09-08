package com.adisastrawan.jetpacksubmission.model

import androidx.annotation.DrawableRes

data class Champion(
    val name : String,
    val description : String,
    @DrawableRes val img : Int,
    val subname : String,
    val role : String,
    val difficulty : String,
    var isFavorite: Boolean = false,
)
