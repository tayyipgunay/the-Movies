package com.tayyipgunay.themovies.data.remote

import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.MoviesDto
import com.tayyipgunay.themovies.data.remote.dto.Search
import com.tayyipgunay.themovies.util.Constanst
import com.tayyipgunay.themovies.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = Constanst.apikey
            ): Response<MoviesDto>

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = Constanst.apikey
    ):Response< MovieDetailDto>



}