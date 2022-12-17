package com.example.spotiplus.ui.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.spotiplus.data.nowPlaying.NowPlayingMovies
import com.example.spotiplus.databinding.ViewPagerCardViewBinding
import com.squareup.picasso.Picasso
import kotlin.math.roundToLong

class ViewPagerAdapter(private val context: Context, private val list: List<NowPlayingMovies>): PagerAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // Crea una vista personalizada para cada elemento de la lista
        val binding = ViewPagerCardViewBinding.inflate(LayoutInflater.from(context), container, false)

        // Obtiene los datos del elemento actual y actualiza la vista
        val itemData = list[position]

        val url = itemData.backdropPath
        binding.viewPagerTittle.text = itemData.title
        binding.viewPagerPoints.text = "vote score: ${itemData.voteAverage}"
        Picasso.get()
            .load("https://image.tmdb.org/t/p/original/$url")
            .into(binding.viewPagerPoster)
        container.addView(binding.root)
        return binding.root
    }
}