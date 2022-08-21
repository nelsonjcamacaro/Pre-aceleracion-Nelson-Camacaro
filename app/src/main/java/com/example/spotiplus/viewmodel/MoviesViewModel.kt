package com.example.spotiplus.viewmodel

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spotiplus.model.*
import com.example.spotiplus.model.repository.MoviesRepository
import com.example.spotiplus.model.repository.RepositoryError
import com.example.spotiplus.model.repository.RepositoryResponse
import com.example.spotiplus.view.MainActivity
import com.example.spotiplus.view.MovieDetails

class MoviesViewModel(private val repository: MoviesRepository):ViewModel() {

    val movies = MutableLiveData<List<Movies>>(null)
    val errorMessage = MutableLiveData<String>(null)
    fun loadMovies(){
        repository.getPopular(object :ResponseListener<PopularMoviesResponse>{
            override fun onResponse(response: RepositoryResponse<PopularMoviesResponse>) {
//                println(response.data.movies[0].title)
                movies.value = response.data.movies
            }

            override fun onError(repositoryError: RepositoryError) {
                errorMessage.value = repositoryError.message
            }

        })
    }

    val movieById = MutableLiveData<MovieByIdResponse?>(null)

    fun loadMovieById(id:Int){
        repository.getMovieById(object : ResponseListener<MovieByIdResponse>{

            override fun onResponse(response: RepositoryResponse<MovieByIdResponse>) {
                movieById.value = response.data
            }

            override fun onError(repositoryError: RepositoryError) {
                errorMessage.value = repositoryError.message
            }
        },id)
    }
}