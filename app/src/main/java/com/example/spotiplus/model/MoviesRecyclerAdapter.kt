package com.example.spotiplus.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotiplus.databinding.CardViewBinding
import com.squareup.picasso.Picasso

class MoviesRecyclerAdapter(private val moviesList:List<Movies>, val listener:MoviesListener?= null):RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {

    interface MoviesListener{
        fun onClickMovie(movie:Movies)
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

    inner class MoviesViewHolder(private val binding: CardViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movie:Movies){
            val url = movie.poster
            binding.tvTittle.text = movie.title
            binding.tvRanking.text = "Calificacion: ${movie.average.toString()}"
            binding.tvYear.text = "Año: ${movie.date}"
            Picasso.get()
                .load("https://image.tmdb.org/t/p/original/$url")
                .into(binding.ivPoster)

            binding.root.setOnClickListener {
                listener?.onClickMovie(movie)
            }
        }
    }

}