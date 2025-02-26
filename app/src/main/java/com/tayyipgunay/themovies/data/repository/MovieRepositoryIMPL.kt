package com.tayyipgunay.themovies.data.repository

import com.tayyipgunay.themovies.data.remote.MovieAPI
import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.data.remote.dto.MoviesDto
import com.tayyipgunay.themovies.domain.model.Movie
import com.tayyipgunay.themovies.domain.repository.MovieRepository
import com.tayyipgunay.themovies.util.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


 class MovieRepositoryIMPL @Inject constructor(private val api: MovieAPI): MovieRepository {


     /*override suspend fun getMovies(search: String): Resource<MoviesDto> {
        val response = api.getMovies(search)
        return response.body()?.let {responseBody->
            Resource.Success(responseBody)
        } ?: Resource.Error("Hata kodu: ${response.code()} - ${response.message()}")
    }*/
     override suspend fun getMovies(search: String): Resource<MoviesDto> {
         return try {
             // API çağrısını yap
             val response = api.getMovies(search)

             // Yanıt başarılıysa işleme devam et
             if (response.isSuccessful) {
                 response.body()?.let { responseBody ->//body null değilse
                     if (!responseBody.search.isNullOrEmpty()) {
                         Resource.Success(responseBody) // Başarılı veri döndür
                     } else {
                         Resource.Error("No movies found.") // Sonuç bulunamadı
                     }
                 } ?: Resource.Error("No movies found.") // Yanıt boş
             } else {
                 // API hatasını yakala
                 Resource.Error("API Error: ${response.code()} - ${response.message()}")
             }

         } catch (e: IOException) {
             // İnternet bağlantısı hatası
             Resource.Error("İnternet bağlantısı yok ")
         } catch (e: Exception) {
             // Beklenmeyen bir hata
             Resource.Error("Unexpected Error: ${e.message}")
         }
     }

     override suspend fun getMovieDetail(imdbId: String): Resource<MovieDetailDto> {
         return try {
             // API çağrısını yap
             val response = api.getMovieDetail(imdbId)

             // Yanıt başarılıysa işleme devam et
             if (response.isSuccessful) {
                 response.body()?.let { responseBody ->

                     if (!responseBody.response.isNullOrEmpty()) {
                         Resource.Success(responseBody) // Başarılı veri döndür
                     } else {
                         Resource.Error("No movies found.") // Sonuç bulunamadı
                     }
                 } ?: Resource.Error("No movies found.") // Yanıt boş
             } else {
                 // API hatasını yakala
                 Resource.Error("API Error: ${response.code()} - ${response.message()}")
             }

         } catch (e: IOException) {
             // İnternet bağlantısı hatası
             Resource.Error("İnternet bağlantısı yok ")
         } catch (e: Exception) {
             // Beklenmeyen bir hata
             Resource.Error("Unexpected Error: ${e.message}")
         }
     }
 }


   /* override suspend fun getMovieDetail(imdbId: String): Resource<MovieDetailDto> {
        val response = api.getMovieDetail(imdbId)
        return response.body()?.let {responseBody->
            Resource.Success(responseBody)
        } ?: Resource.Error("Hata kodu: ${response.code()} - ${response.message()}")
    }
}*/










