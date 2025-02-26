package com.tayyipgunay.themovies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tayyipgunay.themovies.R
import com.tayyipgunay.themovies.databinding.RecyclerRowBinding
import com.tayyipgunay.themovies.domain.model.Movie
import com.tayyipgunay.themovies.presentation.movies.views.MoviesFragmentDirections

class MoviesAdapter(val MoviesList: ArrayList<Movie>) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    // ViewHolder sınıfı, RecyclerView'daki her öğe için bağlanacak görünümü tutar
    class MoviesHolder(var binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        // LayoutInflater kullanarak RecyclerRow layout'unu bağlama işlemi
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(
            inflater,
            R.layout.recycler_row,
            parent,
            false
        )
        return MoviesHolder(binding)
    }

    override fun getItemCount(): Int {
        return MoviesList.size // Listenin boyutunu döndürür
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        // Glide kütüphanesi ile resim yükleme işlemi
        Glide.with(holder.itemView.context)
            .load(MoviesList[position].poster)
            .into(holder.binding.imgPoster)

        // Data Binding kullanarak Movie nesnesini bağlama
        holder.binding.movies = MoviesList[position]

        // Film öğesine tıklandığında detay sayfasına yönlendirme
        holder.itemView.setOnClickListener {
            val imdbID = MoviesList[position].imdbID
            val action = MoviesFragmentDirections.actionMoviesFragmentToMoviesDetailsFragment(imdbID)
            Navigation.findNavController(it).navigate(action)
        }
    }

    // Yeni film listesini güncelleme fonksiyonu
    fun updateMoviesList(newMovieList: List<Movie>?) {
        MoviesList.clear()
        MoviesList.addAll(newMovieList ?: emptyList()) // Null kontrolü ile listeyi güncelle
        notifyDataSetChanged() // RecyclerView'a değişiklik bildir
    }
}

