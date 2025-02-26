package com.tayyipgunay.themovies.domain.model

import com.google.gson.annotations.SerializedName
import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.Rating

data class MovieDetail( // Tek bir filmin detaylarını temsil eden veri modeli

    @SerializedName("Title")
    val title: String, // Filmin başlığı

    @SerializedName("Year")
    val year: String, // Filmin yayın yılı

    @SerializedName("Rated")
    val rated: String, // Yaş derecelendirmesi

    @SerializedName("Released")
    val released: String, // Yayın tarihi

    @SerializedName("Genre")
    val genre: String, // Film türü

    @SerializedName("Director")
    val director: String, // Yönetmen adı

    @SerializedName("Actors")
    val actors: String, // Oyuncu listesi

    @SerializedName("Plot")
    val plot: String, // Filmin özeti

    @SerializedName("Language")
    val language: String, // Kullanılan diller

    @SerializedName("Country")
    val country: String, // Ülke bilgisi

    @SerializedName("Awards")
    val awards: String, // Ödül bilgileri

    @SerializedName("Poster")
    val poster: String, // Poster URL'si

    @SerializedName("imdbRating")
    val imdbRating: String, // IMDb puanı

    @SerializedName("imdbVotes")
    val imdbVotes: String, // IMDb oy sayısı

    @SerializedName("imdbID")
    val imdbID: String, // IMDb kimliği

    @SerializedName("Type")
    val type: String, // Film veya dizi türü

    @SerializedName("DVD")
    val dvd: String? // DVD çıkış tarihi (nullable olabilir)
)
