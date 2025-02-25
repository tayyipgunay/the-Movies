package com.tayyipgunay.themovies.presentation.movies.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tayyipgunay.themovies.data.repository.MovieRepositoryIMPL
import com.tayyipgunay.themovies.databinding.FragmentMoviesBinding
import com.tayyipgunay.themovies.presentation.adapter.MoviesAdapter
import com.tayyipgunay.themovies.presentation.movies.MoviesEvent
import com.tayyipgunay.themovies.presentation.movies.MoviesState
import com.tayyipgunay.themovies.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runInterruptible
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment () : Fragment() {

    // Binding referansı
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!! // Sadece null olmayan binding'e erişim
    private val moviesviewModel: MoviesViewModel by viewModels()
   private val moviesAdapter = MoviesAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // FragmentMoviesBinding'i şişiriyoruz
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewid.layoutManager = LinearLayoutManager(requireContext())// RecyclerView'ın layout manager'ını ayarlıyoruz
        binding.recyclerviewid.adapter=moviesAdapter
        println("onViewCreated başladı")

        // Burada binding ile UI işlemleri yapabilirsiniz
        binding.searchBarid.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchBarid.clearFocus()
                return true
            }

            override fun onQueryTextChange(search: String?): Boolean {
                if (search.isNullOrEmpty()) {
                    // Arama kutusu boşsa "An Search movies" mesajını tetikle
                    moviesviewModel.isBlank()

                } else {
                    // Yeni bir arama sorgusu varsa ViewModel'e gönder
                    moviesviewModel.onEvent(MoviesEvent.Search(search))
                }
                return true

            }
        }

        )


        observe()
    }


    fun observe() {
      moviesviewModel.state.observe(viewLifecycleOwner) { state ->
          state?.let { state ->

              if (state.isloading) {
                  binding.errorMessageid.visibility = View.GONE
                  binding.recyclerviewid.visibility = View.GONE
                  binding.progressBar.visibility = View.VISIBLE
                  binding.errorMessageid.visibility = View.VISIBLE
                  binding.errorMessageid.text = state.error

              }
              if (state.error.isNotBlank()) {
                  binding.recyclerviewid.visibility = View.GONE
                  binding.progressBar.visibility = View.GONE
                  binding.errorMessageid.visibility = View.VISIBLE
                  binding.errorMessageid.text = state.error

              }
              if (state.movies.isNotEmpty()) {
                  println("state movies durumu gözlemleneiyor")
                  println(state.movies.get(0).title + " moviess title değeri gözlemlendi")
                  binding.recyclerviewid.visibility = View.VISIBLE
                  binding.progressBar.visibility = View.GONE
                  binding.errorMessageid.visibility = View.GONE
                  moviesAdapter.updateMoviesList(state.movies)

              }


          }
      }
  }



        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null // Memory leak'leri önlemek için binding'i temizliyoruz
        }


    }


