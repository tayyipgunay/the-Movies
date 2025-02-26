package com.tayyipgunay.themovies.presentation.movie_detail.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.tayyipgunay.themovies.data.remote.dto.MovieDetailDto
import com.tayyipgunay.themovies.databinding.FragmentMoviesDetailsBinding
import com.tayyipgunay.themovies.domain.model.MovieDetail
import com.tayyipgunay.themovies.presentation.movie_detail.MovieDetailEvent
import com.tayyipgunay.themovies.presentation.movies.MoviesState
import com.tayyipgunay.themovies.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Movies_Details_Fragment : Fragment() {

    // Binding referansı, UI bileşenlerine erişim için kullanılır
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!! // Sadece null olmayan binding'e erişim sağlar

    private val movieDetailViewModel: MovieDetailViewModel by viewModels() // ViewModel tanımlaması

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // FragmentMoviesBinding'i inflate ediyoruz
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fragment'e gelen IMDb ID'yi al ve ViewModel üzerinden veri çek
        arguments?.let { bundle ->
            val imdbID = Movies_Details_FragmentArgs.fromBundle(bundle).imdbID
            println(imdbID)
            movieDetailViewModel.onEvent(MovieDetailEvent.GetMovieDetailEvent(imdbID))
        }
        observe() // LiveData gözlemleyici başlatılır
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Memory leak'leri önlemek için binding'i temizliyoruz
    }

    // LiveData gözlemleme fonksiyonu
    fun observe() {
        movieDetailViewModel.state.observe(viewLifecycleOwner) { state ->

            state?.let {
                if (state.isloading) { // Yükleme durumu
                    binding.errorMessageid.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    hideMovieDetails()
                }
                if (state.error.isNotBlank()) { // Hata durumu
                    binding.progressBar.visibility = View.GONE
                    binding.errorMessageid.visibility = View.VISIBLE
                    binding.errorMessageid.text = state.error
                    hideMovieDetails()
                }
                if (state.movie != null) { // Film detayları başarıyla alındığında
                    binding.errorMessageid.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    showMovieDetails()

                    binding.selectedMovie = state.movie // Data Binding ile veriyi bağla
                    Glide.with(this).load(state.movie.poster).into(binding.imgPoster)
                }
            }
        }
    }

    // Film detay bileşenlerini gizleyen fonksiyon
    private fun hideMovieDetails() {
        binding.Yearid.visibility = View.GONE
        binding.ImdbRatingid.visibility = View.GONE
        binding.Actorsid.visibility = View.GONE
        binding.Countryid.visibility = View.GONE
        binding.Directorid.visibility = View.GONE
        binding.Titleid.visibility = View.GONE
        binding.imgPoster.visibility = View.GONE
    }

    // Film detay bileşenlerini gösteren fonksiyon
    private fun showMovieDetails() {
        binding.Yearid.visibility = View.VISIBLE
        binding.ImdbRatingid.visibility = View.VISIBLE
        binding.Actorsid.visibility = View.VISIBLE
        binding.Countryid.visibility = View.VISIBLE
        binding.Directorid.visibility = View.VISIBLE
        binding.Titleid.visibility = View.VISIBLE
        binding.imgPoster.visibility = View.VISIBLE
    }
}



