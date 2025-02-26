package com.tayyipgunay.themovies.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.tayyipgunay.themovies.domain.model.Movie
import retrofit2.Response

data class MoviesDto( // API'den gelen film listesini temsil eden veri sınıfı

    @SerializedName("Search")
    val search: List<Search>, // Filmler listesi

    @SerializedName("totalResults")
    val totalResults: String, // Toplam sonuç sayısı

    @SerializedName("Response")
    val response: String // API yanıt durumu
)

// MoviesDto'yu Movie nesnesi listesine dönüştüren uzantı fonksiyon
fun MoviesDto.toMovieList(): List<Movie> {
    return search.map { search ->
        Movie(
            search.poster, // Filmin afiş URL'si
            search.title,  // Filmin başlığı
            search.year,   // Yayın yılı
            search.imdbID  // IMDb kimliği
        )
    } ?: emptyList() // Eğer veri null gelirse boş liste döndürülür
}


