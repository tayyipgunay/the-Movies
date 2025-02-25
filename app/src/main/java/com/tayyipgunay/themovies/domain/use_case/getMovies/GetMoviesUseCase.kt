package com.tayyipgunay.themovies.domain.use_case.getMovies

import com.tayyipgunay.themovies.data.remote.dto.MoviesDto
import com.tayyipgunay.themovies.data.remote.dto.toMovieDetail
import com.tayyipgunay.themovies.data.remote.dto.toMovieList
import com.tayyipgunay.themovies.domain.model.Movie
import com.tayyipgunay.themovies.domain.model.MovieDetail
import com.tayyipgunay.themovies.domain.repository.MovieRepository
import com.tayyipgunay.themovies.presentation.movies.MoviesState
import com.tayyipgunay.themovies.util.Resource
import retrofit2.Response
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {



   suspend fun executeGetMovies(search: String): Resource<List<Movie>> {
       val result= repository.getMovies(search)
       return when (result) {
           is Resource.Success -> {
               val movies = result.data?.toMovieList()

               movies?.let { movies->
                   Resource.Success(movies)
               } ?: Resource.Error("Film listesi dönüştürülemedi.")
           }

           is Resource.Error -> {
               Resource.Error(result.message?:"Film verisi alınırken bir hata oluştu.")

           }


                is Resource.Loading->Resource.Loading()
       }
   }
}
