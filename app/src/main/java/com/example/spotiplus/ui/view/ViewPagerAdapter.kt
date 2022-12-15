package com.example.spotiplus.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.spotiplus.data.latest.LatestMovies
import com.example.spotiplus.databinding.ViewPagerCardViewBinding
import com.squareup.picasso.Picasso

class ViewPagerAdapter(private val context: Context, private val list: List<LatestMovies>): PagerAdapter() {
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

        val url = itemData.posterPath
        Picasso.get()
            .load("https://image.tmdb.org/t/p/original/$url")
            .into(binding.viewPagerPoster)
        container.addView(binding.root)
        return binding.root
    }
}