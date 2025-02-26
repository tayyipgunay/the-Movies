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

    // Filmleri aramak için kullanılan UseCase sınıfı
    suspend fun executeGetMovies(search: String): Resource<List<Movie>> {
        val result = repository.getMovies(search) // Repository üzerinden film listesini al

        return when (result) {
            is Resource.Success -> {
                val movies = result.data?.toMovieList() // API verisini Movie modeline dönüştür

                movies?.let { movies ->
                    Resource.Success(movies) // Başarılı sonuç döndür
                } ?: Resource.Error("Film listesi dönüştürülemedi.") // Dönüşüm başarısızsa hata mesajı döndür
            }

            is Resource.Error -> {
                Resource.Error(result.message ?: "Film verisi alınırken bir hata oluştu.") // API çağrısı hatalıysa hata mesajı döndür
            }

            is Resource.Loading -> Resource.Loading() // Yüklenme durumunda Loading durumu döndür
        }
    }
}
