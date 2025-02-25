package com.tayyipgunay.themovies.domain.repository

import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.MoviesDto
import com.tayyipgunay.themovies.domain.model.MovieDetail
import com.tayyipgunay.themovies.util.Resource
import retrofit2.Response

interface MovieRepository {


       suspend fun getMovies(search: String):Resource<MoviesDto>

        suspend fun getMovieDetail(imdbId: String): Resource<MovieDetailDto>


    }


