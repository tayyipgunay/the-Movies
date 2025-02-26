package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.tayyipgunay.themovies.domain.model.MovieDetail
import retrofit2.Response

data class MovieDetailDto( // API'den gelen film detaylarını temsil eden veri sınıfı

    @SerializedName("Title")
    val title: String, // Filmin başlığı

    @SerializedName("Year")
    val year: String, // Filmin yayın yılı

    @SerializedName("Rated")
    val rated: String, // Yaş derecelendirmesi

    @SerializedName("Released")
    val released: String, // Yayın tarihi

    @SerializedName("Runtime")
    val runtime: String, // Filmin süresi

    @SerializedName("Genre")
    val genre: String, // Film türü

    @SerializedName("Director")
    val director: String, // Yönetmen adı

    @SerializedName("Writer")
    val writer: String, // Senarist bilgisi

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

    @SerializedName("Ratings")
    val ratings: List<Rating>, // Film puanlamaları

    @SerializedName("Metascore")
    val metascore: String, // Metascore puanı

    @SerializedName("imdbRating")
    val imdbRating: String, // IMDb puanı

    @SerializedName("imdbVotes")
    val imdbVotes: String, // IMDb oy sayısı

    @SerializedName("imdbID")
    val imdbID: String, // IMDb ID'si

    @SerializedName("Type")
    val type: String, // Film veya dizi türü

    @SerializedName("DVD")
    val dvd: String?, // DVD çıkış tarihi (nullable olabilir)

    @SerializedName("BoxOffice")
    val boxOffice: String?, // Gişe hasılatı (nullable olabilir)

    @SerializedName("Production")
    val production: String?, // Yapımcı bilgisi (nullable olabilir)

    @SerializedName("Website")
    val website: String?, // Resmi web sitesi (nullable olabilir)

    @SerializedName("Response")
    val response: String // API'den gelen yanıt durumu
)

// MovieDetailDto'yu MovieDetail veri sınıfına dönüştüren uzantı fonksiyon
fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        title, year, rated, released, genre, director, actors, plot, language,
        country, awards, poster, imdbRating, imdbVotes, imdbID, type, dvd
    )
}
