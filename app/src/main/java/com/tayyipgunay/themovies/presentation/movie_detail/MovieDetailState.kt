package com.tayyipgunay.themovies.presentation.movie_detail

import com.tayyipgunay.themovies.domain.model.Movie
import com.tayyipgunay.themovies.domain.model.MovieDetail


data class MovieDetailState(
        val isloading:Boolean=false,
        val movie: MovieDetail? = null,
        val error:String="",

    )
