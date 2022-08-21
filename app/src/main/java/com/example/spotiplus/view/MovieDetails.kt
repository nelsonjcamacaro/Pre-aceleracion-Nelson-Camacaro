package com.example.spotiplus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.spotiplus.R
import com.example.spotiplus.databinding.ActivityMovieDetailsBinding
import com.example.spotiplus.model.MovieByIdResponse
import com.example.spotiplus.model.Movies
import com.example.spotiplus.viewmodel.MoviesViewModel
import com.example.spotiplus.viewmodel.MoviesViewModelFactory
import com.squareup.picasso.Picasso

class MovieDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel : MoviesViewModel by viewModels(
        factoryProducer = { MoviesViewModelFactory() }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.getIntExtra(KEY1,0)

        viewModel.movieById.observe(this){
            if (it != null) {
                binding.tvTitle.text = it.title
                binding.tvOverview.text = it.overView
                binding.tvDate.text ="Fecha: ${it.date}"
                binding.tvTagLine.text = it.tagLine
                binding.tvAverage.text ="Puntuacion: ${it.average.toString()}"

                Picasso.get()
                    .load("https://image.tmdb.org/t/p/original/${it.poster}")
                    .into(binding.imageViewPoster)

            }
//            binding.tvTitle.text = it.title
        }

        viewModel.errorMessage.observe(this){
            Toast.makeText(this@MovieDetails, "Parece que hubo un error, el servidor no ha encontrado la informacion"
                , Toast.LENGTH_LONG).show()
        }
        viewModel.loadMovieById(movieId)
    }
        companion object{
        const val KEY1 ="Id of movie"
    }
}

