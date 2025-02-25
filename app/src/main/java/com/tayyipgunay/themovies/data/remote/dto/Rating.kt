package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName

 data class Rating(//detail içindeki ayrı bir json
    @SerializedName("Source")
    val source: String,

    @SerializedName("Value")
    val value: String
 )