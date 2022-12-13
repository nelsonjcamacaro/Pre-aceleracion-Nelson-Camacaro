package com.example.spotiplus.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotiplus.data.PopularTvSeries
import com.example.spotiplus.data.UpcomingMovies
import com.example.spotiplus.databinding.CardViewBinding
import com.squareup.picasso.Picasso

class PopularTvSeriesRecyclerAdapter (private val tvSeriesList:List<PopularTvSeries>):
    RecyclerView.Adapter<PopularTvSeriesRecyclerAdapter.PopularTvSeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvSeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardViewBinding.inflate(layoutInflater, parent, false)
        return PopularTvSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvSeriesList.size
    }

    override fun onBindViewHolder(holder: PopularTvSeriesViewHolder, position: Int) {
        holder.bind(tvSeriesList[position])
    }

    inner class PopularTvSeriesViewHolder(private val binding: CardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: PopularTvSeries) {
            val url = series.posterPath
            Picasso.get()
                .load("https://image.tmdb.org/t/p/original/$url")
                .into(binding.ivPoster)
        }

    }
}