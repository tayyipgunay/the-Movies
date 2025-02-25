package com.tayyipgunay.themovies.presentation.movies

sealed class MoviesEvent {
data class Search(val search: String) : MoviesEvent()

}