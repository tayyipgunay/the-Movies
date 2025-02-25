package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Search (// movies içindeki search  adında json kısmı.
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("imdbID")
    val imdbID: String,

    @SerializedName("Poster")
    val poster: String
)