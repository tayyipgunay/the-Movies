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

class MoviesAdapter(val MoviesList:ArrayList<Movie>) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {


    class MoviesHolder(var binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {


      /*  val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(binding)*/
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(inflater,
            R.layout.recycler_row,parent,false)
        return MoviesHolder(binding)


    }

    override fun getItemCount(): Int {

        return MoviesList.size
        }


    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {

       // holder.binding.Titleid.text=MoviesList[position].title
        //holder.binding.Yearid.text=MoviesList[position].year
        Glide.with(holder.itemView.context).load(MoviesList[position].poster).into(holder.binding.imgPoster)
//buraya dataBinding ekle
        holder.binding.movies=MoviesList[position]

        holder.itemView.setOnClickListener{
            val imdbID=MoviesList[position].imdbID
           val action = MoviesFragmentDirections.actionMoviesFragmentToMoviesDetailsFragment(imdbID)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun updateMoviesList(newMovieList: List<Movie>?) {
        MoviesList.clear()
        MoviesList.addAll(newMovieList ?: emptyList())
        notifyDataSetChanged()




    }
}
