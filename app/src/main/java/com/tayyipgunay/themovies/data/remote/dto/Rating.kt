package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Rating( // Film detaylarında yer alan derecelendirme bilgilerini temsil eder

    @SerializedName("Source")
    val source: String, // Derecelendirme kaynağı (IMDb, Rotten Tomatoes vb.)

    @SerializedName("Value")
    val value: String // Derecelendirme değeri (örneğin: "7.5/10" veya "90%")
)
