package com.tayyipgunay.themovies.domain.use_case.getMovieDetails

import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.MoviesDto
import com.tayyipgunay.themovies.data.remote.dto.toMovieDetail
import com.tayyipgunay.themovies.domain.model.MovieDetail
import com.tayyipgunay.themovies.domain.repository.MovieRepository
import com.tayyipgunay.themovies.presentation.movies.MoviesState
import com.tayyipgunay.themovies.util.Resource
import retrofit2.Response
import javax.inject.Inject

class GetMoviesDetailsUseCase
    @Inject constructor(private val repository: MovieRepository) {


    suspend fun executeDetailMovies(imdbId: String): Resource<MovieDetail> {
        val result = repository.getMovieDetail(imdbId)
        return when (result) {
            is Resource.Success -> {
                val movieDetail = result.data?.toMovieDetail()
                movieDetail?.let { movieDetail->
                    Resource.Success(movieDetail)
                } ?: Resource.Error("Film detayları dönüştürülemedi.")
            }
            is Resource.Error -> Resource.Error(result.message ?: "Bir hata oluştu.")

            is Resource.Loading -> Resource.Loading()

        }
    }
}



