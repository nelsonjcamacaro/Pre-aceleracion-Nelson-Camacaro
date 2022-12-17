package com.example.spotiplus.ui.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotiplus.data.topRated.TopRatedMovies
import com.example.spotiplus.databinding.CardViewBinding
import com.squareup.picasso.Picasso

class TopRatedRecyclerAdapter(private val topRatedList:List<TopRatedMovies>):
    RecyclerView.Adapter<TopRatedRecyclerAdapter.TopRatedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardViewBinding.inflate(layoutInflater,parent,false)
        return TopRatedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return topRatedList.size
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        holder.bind(topRatedList[position])
    }

    inner class TopRatedViewHolder(private val binding: CardViewBinding):RecyclerView.ViewHolder(binding.root) {
         fun bind(movies: TopRatedMovies){
             val url = movies.poster_path
             Picasso.get()
                 .load("https://image.tmdb.org/t/p/original/$url")
                 .into(binding.ivPoster)
         }

    }
}