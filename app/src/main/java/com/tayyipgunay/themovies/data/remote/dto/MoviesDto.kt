package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.tayyipgunay.themovies.domain.model.Movie
import retrofit2.Response

data class MoviesDto (//genel json movies içindeki jsonlar.


    @SerializedName("Search")
    val search: List<Search>,

    @SerializedName("totalResults")
    val totalResults: String,

    @SerializedName("Response")
    val response: String
)

fun MoviesDto.toMovieList():List<Movie> {
return search.map { search ->
    Movie(
        search.poster,
        search.title,
        search.year,
        search.imdbID
    )


}?: emptyList()
}

