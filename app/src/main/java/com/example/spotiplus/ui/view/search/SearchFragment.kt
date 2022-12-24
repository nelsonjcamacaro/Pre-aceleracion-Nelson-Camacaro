package com.example.spotiplus.ui.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotiplus.R
import com.example.spotiplus.data.popular.Movies
import com.example.spotiplus.databinding.FragmentHomeBinding
import com.example.spotiplus.databinding.FragmentSearchBinding
import com.example.spotiplus.ui.view.home.MoviesRecyclerAdapter
import com.example.spotiplus.ui.viewmodel.MoviesViewModel
import com.example.spotiplus.ui.viewmodel.MoviesViewModelFactory

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter:MoviesRecyclerAdapter
    private val viewModel : MoviesViewModel by viewModels(
        factoryProducer = { MoviesViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewSearch.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        viewModel.movies.observe(viewLifecycleOwner){ listOfMovies ->
            if (listOfMovies != null){
                val adapter = MoviesRecyclerAdapter(listOfMovies,object: MoviesRecyclerAdapter.MoviesListener{
                    override fun onClickMovie(movie: Movies) {
                        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
                    }
                })
                binding.searchView.addTextChangedListener{
                    val moviesFiltered = listOfMovies.filter { movies ->
                        movies.title.lowercase().contains(it.toString())}
                    adapter.updateMovies(moviesFiltered)
                }
                binding.recyclerViewSearch.adapter = adapter
            }
        }
        viewModel.loadMovies()
    }
}