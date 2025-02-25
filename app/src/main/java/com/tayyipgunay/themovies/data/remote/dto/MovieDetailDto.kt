package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.tayyipgunay.themovies.domain.model.MovieDetail
import retrofit2.Response

data class MovieDetailDto(//detail içindeki ilk kısım jsonlar
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Rated")
    val rated: String,

    @SerializedName("Released")
    val released: String,

    @SerializedName("Runtime")
    val runtime: String,

    @SerializedName("Genre")
    val genre: String,

    @SerializedName("Director")
    val director: String,

    @SerializedName("Writer")
    val writer: String,

    @SerializedName("Actors")
    val actors: String,

    @SerializedName("Plot")
    val plot: String,

    @SerializedName("Language")
    val language: String,

    @SerializedName("Country")
    val country: String,

    @SerializedName("Awards")
    val awards: String,

    @SerializedName("Poster")
    val poster: String,

    @SerializedName("Ratings")
    val ratings: List<Rating>,

    @SerializedName("Metascore")
    val metascore: String,

    @SerializedName("imdbRating")
    val imdbRating: String,

    @SerializedName("imdbVotes")
    val imdbVotes: String,

    @SerializedName("imdbID")
    val imdbID: String,

    @SerializedName("Type")
    val type: String,

    @SerializedName("DVD")
    val dvd: String?, // Nullable çünkü bazen boş dönebilir

    @SerializedName("BoxOffice")
    val boxOffice: String?, // Nullable çünkü bazen boş dönebilir

    @SerializedName("Production")
    val production: String?, // Nullable çünkü bazen boş dönebilir

    @SerializedName("Website")
    val website: String?, // Nullable çünkü bazen boş dönebilir

    @SerializedName("Response")
    val response: String
)
fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        title, year, rated, released, genre, director, actors, plot, language,
        country, awards, poster, imdbRating, imdbVotes, imdbID, type, dvd
    )
}