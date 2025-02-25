package com.tayyipgunay.themovies.presentation.movie_detail.views

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.toMovieDetail
import com.tayyipgunay.themovies.data.remote.dto.toMovieList
import com.tayyipgunay.themovies.domain.model.MovieDetail
import com.tayyipgunay.themovies.domain.use_case.getMovieDetails.GetMoviesDetailsUseCase
import com.tayyipgunay.themovies.domain.use_case.getMovies.GetMoviesUseCase
import com.tayyipgunay.themovies.presentation.baseViewModel.BaseViewModel
import com.tayyipgunay.themovies.presentation.movie_detail.MovieDetailEvent
import com.tayyipgunay.themovies.presentation.movie_detail.MovieDetailState
import com.tayyipgunay.themovies.presentation.movies.MoviesEvent
import com.tayyipgunay.themovies.presentation.movies.MoviesState
import com.tayyipgunay.themovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
@HiltViewModel
class MovieDetailViewModel  @Inject constructor(
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase): BaseViewModel() {

    val _state = MutableLiveData<MovieDetailState>()// dışarıya sunma
    val state: LiveData<MovieDetailState> = _state//dışarıya salt okuma olarak sunma*/


   fun getMovie(imdbId: String) {
       println("getMovie çağrıldı: $imdbId")

       // Genel hata yönetimi için CoroutineExceptionHandler
       /*val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
           val errorMessage = when (throwable) {
               is NullPointerException -> "Film detayları bulunamadı." // Eksik veri
               is IOException -> "İnternet bağlantısı yok." // İnternet hatası
               else -> throwable.localizedMessage ?: "Beklenmeyen bir hata oluştu."
           }
           println("CoroutineExceptionHandler Hatası: $errorMessage")
           _state.postValue(
               MovieDetailState(
                   isloading = false,
                   movie = null,
                   error = errorMessage
               )
           )
       }*/

       // Yükleniyor durumunu başlat
       _state.postValue(MovieDetailState(isloading = true))

       // Coroutine başlat
       viewModelScope.launch(Dispatchers.IO) {

               // Film detaylarını al
               val result = getMoviesDetailsUseCase.executeDetailMovies(imdbId)

               // Sonucu işlemek için main thread'e geçiş yap
               withContext(Dispatchers.Main) {
                   handleDetailResult(result)
               }

           }
       }


    // API sonuçlarını işleyen fonksiyon
    private fun handleDetailResult(result: Resource<MovieDetail>) {
        when (result) {
            is Resource.Success -> {
                val movieDetail = result.data
                    _state.postValue(
                        MovieDetailState(
                            isloading = false,
                            movie = movieDetail,
                            error = result.message.toString()
                        )
                    )

            }
            is Resource.Error -> {
                _state.postValue(
                    MovieDetailState(
                        isloading = false,
                        movie = null,
                        error = result.message ?: "Bir hata oluştu."
                    )
                )
            }
            is Resource.Loading -> {
                _state.postValue(MovieDetailState(isloading = true))
            }
        }
    }


    fun onEvent(event: MovieDetailEvent) { // Gelen event'i yönetir.
        when (event) { // Gelen event'in türüne göre işlem yapar.

            is MovieDetailEvent.GetMovieDetailEvent -> {
                println("search event tetiklendi ve çalışacak mı")//

                getMovie(event.imdbId) // Arama event'indeki "search" parametresine göre getMovies() çağrılır.

                println("search event tetiklendi ve çalıştı") // Arama event'inin tamamlandığını loglar.

            }
        }


        }


    }


