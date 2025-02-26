package com.tayyipgunay.themovies.domain.repository

import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.MoviesDto
import com.tayyipgunay.themovies.domain.model.MovieDetail
import com.tayyipgunay.themovies.util.Resource
import retrofit2.Response

interface MovieRepository {

    // Film arama işlemi için fonksiyon
    suspend fun getMovies(search: String): Resource<MoviesDto>
    // Parametre: search -> Kullanıcının aramak istediği film adı
    // Dönen değer: Resource<MoviesDto> -> API'den gelen film listesini kapsayan bir kaynak

    // Belirli bir filmin detaylarını almak için fonksiyon
    suspend fun getMovieDetail(imdbId: String): Resource<MovieDetailDto>
    // Parametre: imdbId -> IMDb kimliği ile belirli bir filmin detaylarını alır
    // Dönen değer: Resource<MovieDetailDto> -> API'den gelen film detaylarını kapsayan bir kaynak
}


