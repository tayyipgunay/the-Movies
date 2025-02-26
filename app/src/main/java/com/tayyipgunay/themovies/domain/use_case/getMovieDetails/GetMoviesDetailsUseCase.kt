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

class GetMoviesDetailsUseCase @Inject constructor(private val repository: MovieRepository) {

    // Belirli bir filmin detaylarını almak için kullanılan UseCase sınıfı
    suspend fun executeDetailMovies(imdbId: String): Resource<MovieDetail> {
        val result = repository.getMovieDetail(imdbId) // Repository üzerinden film detaylarını al

        return when (result) {
            is Resource.Success -> {
                val movieDetail = result.data?.toMovieDetail() // API verisini MovieDetail modeline dönüştür
                movieDetail?.let { movieDetail ->
                    Resource.Success(movieDetail) // Başarılı sonuç döndür
                } ?: Resource.Error("Film detayları dönüştürülemedi.") // Dönüşüm başarısızsa hata mesajı döndür
            }
            is Resource.Error -> Resource.Error(result.message ?: "Bir hata oluştu.") // API çağrısı hatalıysa hata mesajı döndür
            is Resource.Loading -> Resource.Loading() // Yüklenme durumunda Loading durumu döndür
        }
    }
}




