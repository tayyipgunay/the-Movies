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

    @Provides
    @Singleton
    fun provideMovieApi():MovieAPI{
        return Retrofit.Builder().baseUrl(Constanst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryIMPL(api)

    }

}