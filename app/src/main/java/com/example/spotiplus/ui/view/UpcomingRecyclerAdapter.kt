package com.example.spotiplus.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotiplus.data.UpcomingMovies
import com.example.spotiplus.databinding.CardViewBinding
import com.squareup.picasso.Picasso

class UpcomingRecyclerAdapter (private val upcomingList:List<UpcomingMovies>):
    RecyclerView.Adapter<UpcomingRecyclerAdapter.UpcomingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardViewBinding.inflate(layoutInflater, parent, false)
        return UpcomingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(upcomingList[position])
    }

    inner class UpcomingViewHolder(private val binding: CardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: UpcomingMovies) {
            val url = movies.poster_path
            Picasso.get()
                .load("https://image.tmdb.org/t/p/original/$url")
                .into(binding.ivPoster)
        }
    }
}