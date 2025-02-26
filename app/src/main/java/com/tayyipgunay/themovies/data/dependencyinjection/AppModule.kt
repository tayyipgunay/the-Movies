package com.tayyipgunay.themovies.data.dependencyinjection

import com.tayyipgunay.themovies.data.remote.MovieAPI
import com.tayyipgunay.themovies.data.repository.MovieRepositoryIMPL
import com.tayyipgunay.themovies.domain.repository.MovieRepository
import com.tayyipgunay.themovies.util.Constanst
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit API servisini sağlayan fonksiyon
    @Provides
    @Singleton
    fun provideMovieApi(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(Constanst.BASE_URL) // Temel URL belirlenir
            .addConverterFactory(GsonConverterFactory.create()) // JSON dönüşümü için Gson kullanılır
            .build()
            .create(MovieAPI::class.java) // API arayüzü oluşturulur
    }

    // MovieRepository bağımlılığını sağlayan fonksiyon
    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryIMPL(api) // MovieRepositoryIMPL, API bağımlılığı ile oluşturulur
    }
}
