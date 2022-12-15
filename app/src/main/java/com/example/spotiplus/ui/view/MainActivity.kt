package com.example.spotiplus.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotiplus.data.popular.Movies
import com.example.spotiplus.data.topRated.TopRatedMovies
import com.example.spotiplus.data.topRated.TopRatedMoviesResponse
import com.example.spotiplus.databinding.ActivityMainBinding
import com.example.spotiplus.ui.viewmodel.MoviesViewModel
import com.example.spotiplus.ui.viewmodel.MoviesViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel : MoviesViewModel by viewModels(
        factoryProducer = { MoviesViewModelFactory() }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView4.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        viewModel.movies.observe(this){ listOfMovies ->
            if (listOfMovies != null){
                val adapter = MoviesRecyclerAdapter(listOfMovies,object: MoviesRecyclerAdapter.MoviesListener{
                    override fun onClickMovie(movie: Movies) {
                        Toast.makeText(this@MainActivity, movie.title,Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, MovieDetails::class.java)
                            .apply{ putExtra(MovieDetails.KEY1,movie.id) }
                        startActivity(intent)
                    }

                })
                binding.recyclerView.adapter = adapter
            }

        }

        viewModel.topRatedMovies.observe(this){listOfTopRatedMovies ->
            if (listOfTopRatedMovies != null){
                val adapter = TopRatedRecyclerAdapter(listOfTopRatedMovies.movies)
                binding.recyclerView2.adapter = adapter
            }
        }

        viewModel.upcomingMovies.observe(this){ listOfUpcomingMovies ->
            if (listOfUpcomingMovies != null){
                val adapter = UpcomingRecyclerAdapter(listOfUpcomingMovies.movies)
                binding.recyclerView3.adapter = adapter
            }else{
                Toast.makeText(this@MainActivity,"la parte de Upcoming retorna NULL"
                    ,Toast.LENGTH_LONG).show()
            }

        }

        viewModel.popularTvSeries.observe(this){ listOfPopularTvSeries ->
            if (listOfPopularTvSeries!= null){
                val adapter = PopularTvSeriesRecyclerAdapter(listOfPopularTvSeries.series)
                    binding.recyclerView4.adapter = adapter

            }

        }

        viewModel.errorMessage.observe(this){
            if (it != null){
                Toast.makeText(this@MainActivity,"Parece que hubo un error, el servidor no ha encontrado la informacion"
                    ,Toast.LENGTH_LONG).show()
            }
        }


        viewModel.loadMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcoming()
        viewModel.getPopularTVSeries()
    }
}