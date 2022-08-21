package com.example.spotiplus.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotiplus.databinding.ActivityMainBinding
import com.example.spotiplus.model.Movies
import com.example.spotiplus.model.MoviesRecyclerAdapter
import com.example.spotiplus.model.MoviesRemoteDataSource
import com.example.spotiplus.viewmodel.MoviesViewModel
import com.example.spotiplus.viewmodel.MoviesViewModelFactory
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel : MoviesViewModel by viewModels(
        factoryProducer = {MoviesViewModelFactory()}
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.movies.observe(this){ listOfMovies ->
            if (listOfMovies != null){
                val adapter = MoviesRecyclerAdapter(listOfMovies,object: MoviesRecyclerAdapter.MoviesListener{
                    override fun onClickMovie(movie: Movies) {
                        Toast.makeText(this@MainActivity, movie.title,Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity,MovieDetails::class.java)
                            .apply{ putExtra(MovieDetails.KEY1,movie.id) }
                        startActivity(intent)
                    }

                })
                binding.recyclerView.adapter = adapter
            }

        }

        viewModel.errorMessage.observe(this){
            Toast.makeText(this@MainActivity,"Parece que hubo un error, el servidor no ha encontrado la informacion"
                ,Toast.LENGTH_LONG).show()
        }

        viewModel.loadMovies()
    }
}