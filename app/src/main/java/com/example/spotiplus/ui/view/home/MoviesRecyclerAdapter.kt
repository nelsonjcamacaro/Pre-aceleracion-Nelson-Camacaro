package com.example.spotiplus.ui.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotiplus.data.popular.Movies
import com.example.spotiplus.databinding.CardViewBinding
import com.squareup.picasso.Picasso

class MoviesRecyclerAdapter(private var moviesList:List<Movies>, val listener: MoviesListener?= null):
    RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {

    interface MoviesListener{
        fun onClickMovie(movie: Movies)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardViewBinding.inflate(layoutInflater,parent,false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updateMovies(movies:List<Movies>){
        this.moviesList = movies
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: CardViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movies){
            val url = movie.poster
            Picasso.get()
                .load("https://image.tmdb.org/t/p/original/$url")
                .into(binding.ivPoster)

            binding.root.setOnClickListener {
                listener?.onClickMovie(movie)
            }
        }
    }

}