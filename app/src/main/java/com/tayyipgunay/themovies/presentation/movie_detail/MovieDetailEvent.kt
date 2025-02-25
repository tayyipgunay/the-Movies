package com.tayyipgunay.themovies.presentation.movie_detail

sealed class MovieDetailEvent {
    data class GetMovieDetailEvent(val imdbId: String) : MovieDetailEvent()

}