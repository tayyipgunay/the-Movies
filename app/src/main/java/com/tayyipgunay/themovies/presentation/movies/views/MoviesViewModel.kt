package com.tayyipgunay.themovies.presentation.movies.views

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tayyipgunay.themovies.data.remote.dto.MoviesDto
import com.tayyipgunay.themovies.data.remote.dto.toMovieList
import com.tayyipgunay.themovies.domain.model.Movie
import com.tayyipgunay.themovies.domain.use_case.getMovies.GetMoviesUseCase
import com.tayyipgunay.themovies.presentation.baseViewModel.BaseViewModel
import com.tayyipgunay.themovies.presentation.movie_detail.MovieDetailState
import com.tayyipgunay.themovies.presentation.movies.MoviesEvent
import com.tayyipgunay.themovies.presentation.movies.MoviesState
import com.tayyipgunay.themovies.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    val _state = MutableLiveData<MoviesState>()//
    val state: LiveData<MoviesState> = _state//dışarıya salt okuma olarak sunma

    fun isBlank() {
        _state.value = MoviesState(error = "An Search movies")
        return
    }


    fun getMovies(search: String) {
        println("getMovies çağrıldı: $search")

        // Genel hata yönetimi için CoroutineExceptionHandler
        /*val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            val errorMessage = when (throwable) {
                is NullPointerException -> "Aradığınız kriterlere uygun film bulunamadı." // Eksik veri
                is IOException -> "İnternet bağlantısı yok." // İnternet hatası
                else -> throwable.localizedMessage ?: "Beklenmeyen bir hata oluştu."
            }*/
           // println("CoroutineExceptionHandler Hatası: $errorMessage")
           /* _state.postValue(
                MoviesState(
                    isloading = false,
                    movies = emptyList(),
                    error = errorMessage
                )
            )*/


        // Yükleniyor durumunu başlat
        _state.postValue(MoviesState(isloading = true))

        // Coroutine başlat
        viewModelScope.launch(Dispatchers.IO) {

                // Film listesini al
                val result = getMoviesUseCase.executeGetMovies(search)

                // Sonucu işlemek için main thread'e geçiş yap
                withContext(Dispatchers.Main) {
                    handleMoviesResult(result)
                }



        }
    }

    // API sonuçlarını işleyen fonksiyon
    private fun handleMoviesResult(result: Resource<List<Movie>>) {
        when (result) {
            is Resource.Success -> {
                val movies = result.data
                if (movies.isNullOrEmpty()) {
                    _state.postValue(
                        MoviesState(
                            isloading = false,
                            movies = emptyList(),
                            error = result.message.toString()
                        )
                    )
                } else {
                    _state.postValue(
                        MoviesState(
                            isloading = false,
                            movies = movies,
                            error = result.message.toString()
                        )
                    )
                }
                println("abdbbdd")
            }
            is Resource.Error -> {
                _state.postValue(
                    MoviesState(
                        isloading = false,
                        movies = emptyList(),
                        error = result.message ?: "Bir hata oluştu."
                    )
                )
            }
            is Resource.Loading -> {
                _state.postValue(MoviesState(isloading = true))
            }
        }
    }





    fun onEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.Search -> {
                println("search event tetiklendi ve çalışacak mı")

                getMovies(event.search)

                println("search event tetiklendi ve çalıştı")
            }
        }

    }
}


