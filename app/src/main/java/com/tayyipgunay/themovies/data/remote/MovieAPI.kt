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

    // Filmleri aramak için kullanılan API çağrısı
    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String, // Kullanıcının aradığı film adı
        @Query("apikey") apiKey: String = Constanst.apikey // API anahtarı (varsayılan olarak belirlenmiş)
    ): Response<MoviesDto>

    // Belirli bir filmin detaylarını almak için kullanılan API çağrısı
    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String, // IMDb kimliği ile film detaylarını alır
        @Query("apikey") apiKey: String = Constanst.apikey // API anahtarı (varsayılan olarak belirlenmiş)
    ): Response<MovieDetailDto>
}
