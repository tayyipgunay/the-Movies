package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Search( // Movies API içindeki "Search" adlı JSON nesnesini temsil eder

    @SerializedName("Title")
    val title: String, // Filmin başlığı

    @SerializedName("Year")
    val year: String, // Filmin yayın yılı

    @SerializedName("imdbID")
    val imdbID: String, // IMDb kimliği

    @SerializedName("Poster")
    val poster: String // Filmin afiş URL'si
)
