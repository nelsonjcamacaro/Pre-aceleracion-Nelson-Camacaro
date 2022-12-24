package com.example.spotiplus.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotiplus.R
import com.example.spotiplus.data.latest.LatestMovies
import com.example.spotiplus.data.popular.Movies
import com.example.spotiplus.data.topRated.TopRatedMovies
import com.example.spotiplus.data.topRated.TopRatedMoviesResponse
import com.example.spotiplus.databinding.ActivityMainBinding
import com.example.spotiplus.ui.viewmodel.MoviesViewModel
import com.example.spotiplus.ui.viewmodel.MoviesViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}