package com.example.test

import com.google.gson.annotations.SerializedName


data class Gym (
    val id:Int ,
    @SerializedName("gym_name")
    val name:String,
    @SerializedName("gym_location")
    val place:String,
    var isFavourite : Boolean=false)