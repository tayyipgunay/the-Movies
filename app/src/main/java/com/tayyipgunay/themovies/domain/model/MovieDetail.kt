package com.tayyipgunay.themovies.domain.model

import com.google.gson.annotations.SerializedName
import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.Rating

data class MovieDetail (
@SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Rated")
    val rated: String,

    @SerializedName("Released")
    val released: String,

    @SerializedName("Genre")
    val genre: String,

    @SerializedName("Director")
    val director: String,



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

    )

