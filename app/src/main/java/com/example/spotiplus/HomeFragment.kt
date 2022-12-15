package com.example.spotiplus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotiplus.data.popular.Movies
import com.example.spotiplus.databinding.FragmentHomeBinding
import com.example.spotiplus.ui.view.*
import com.example.spotiplus.ui.viewmodel.MoviesViewModel
import com.example.spotiplus.ui.viewmodel.MoviesViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MoviesViewModel by viewModels(
        factoryProducer = { MoviesViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView2.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView4.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        viewModel.movies.observe(viewLifecycleOwner){ listOfMovies ->
            if (listOfMovies != null){
                val adapter = MoviesRecyclerAdapter(listOfMovies,object: MoviesRecyclerAdapter.MoviesListener{
                    override fun onClickMovie(movie: Movies) {
                        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
                        /*val intent = Intent(this@HomeFragment, MovieDetails::class.java)
                            .apply{ putExtra(MovieDetails.KEY1,movie.id) }
                        startActivity(intent)*/
                    }

                })
                binding.recyclerView.adapter = adapter
            }

        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner){listOfTopRatedMovies ->
            if (listOfTopRatedMovies != null){
                val adapter = TopRatedRecyclerAdapter(listOfTopRatedMovies.movies)
                binding.recyclerView2.adapter = adapter
            }
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner){ listOfUpcomingMovies ->
            if (listOfUpcomingMovies != null){
                val adapter = UpcomingRecyclerAdapter(listOfUpcomingMovies.movies)
                binding.recyclerView3.adapter = adapter
            }
        }

        viewModel.popularTvSeries.observe(viewLifecycleOwner){ listOfPopularTvSeries ->
            if (listOfPopularTvSeries!= null){
                val adapter = PopularTvSeriesRecyclerAdapter(listOfPopularTvSeries.series)
                binding.recyclerView4.adapter = adapter

            }

        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner){listOfNowPlayingMovies ->
            if (listOfNowPlayingMovies !=null){
                val adapter = ViewPagerAdapter(requireContext(), listOfNowPlayingMovies.movies)
                binding.viewPager.adapter = adapter
            }else{
                Toast.makeText(context,"la parte de latest retorna NULL"
                    , Toast.LENGTH_LONG).show()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){
            if (it != null){
                Toast.makeText(context,"Parece que hubo un error, el servidor no ha encontrado la informacion"
                    , Toast.LENGTH_LONG).show()
            }
        }


        viewModel.loadMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcoming()
        viewModel.getPopularTVSeries()
        viewModel.getNowPlayingMovies()
    }

}