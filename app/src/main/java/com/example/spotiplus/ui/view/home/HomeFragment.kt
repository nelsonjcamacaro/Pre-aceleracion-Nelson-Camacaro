package com.example.spotiplus.ui.view.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotiplus.data.popular.Movies
import com.example.spotiplus.databinding.FragmentHomeBinding
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

        recyclerViewInstance(binding.recyclerView)
        recyclerViewInstance(binding.recyclerView2)
        recyclerViewInstance(binding.recyclerView3)
        recyclerViewInstance(binding.recyclerView4)
        viewModel.loadMovies()
        viewModel.getTopRatedMovies()
        viewModel.getUpcoming()
        viewModel.getPopularTVSeries()
        viewModel.getNowPlayingMovies()
        recyclersOk()

    }


    private fun shimmerVisibility(){
        binding.shimmerContainer.isVisible = false
        binding.scrollView.isVisible = true
        binding.header.isVisible = true
    }

    private fun recyclersOk(){
        Handler(Looper.getMainLooper()).postDelayed({

            shimmerVisibility()

            viewModel.movies.observe(viewLifecycleOwner){ listOfMovies ->
                if (listOfMovies != null){
                    val adapter = MoviesRecyclerAdapter(listOfMovies,object: MoviesRecyclerAdapter.MoviesListener{
                        override fun onClickMovie(movie: Movies) {
                            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
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

                }
            }

            viewModel.errorMessage.observe(viewLifecycleOwner){
                if (it != null){
                    Toast.makeText(context,"Parece que hubo un error, el servidor no ha encontrado la informacion"
                        , Toast.LENGTH_LONG).show()
                }
            }
        },1500)
    }

    private fun recyclerViewInstance(recyclerId: RecyclerView){
        recyclerId.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }
}