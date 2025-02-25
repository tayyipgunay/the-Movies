package com.tayyipgunay.themovies.presentation.movies

import com.tayyipgunay.themovies.domain.model.Movie

data class MoviesState(
    val isloading:Boolean=false,
    val movies:List<Movie> = emptyList(),
    val error:String="",
    val search : String = "ess"


)