package com.tayyipgunay.themovies.domain.model

data class Movie( // Filmleri temsil eden temel veri modeli
    val poster: String, // Filmin afiş URL'si
    val title: String, // Filmin başlığı
    val year: String, // Filmin yayın yılı
    val imdbID: String // IMDb kimliği
)
