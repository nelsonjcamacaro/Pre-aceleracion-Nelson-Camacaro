package com.example.spotiplus.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spotiplus.data.*
import com.example.spotiplus.data.popular.Movies
import com.example.spotiplus.data.popular.PopularMoviesResponse
import com.example.spotiplus.data.repository.MoviesRepository
import com.example.spotiplus.data.repository.RepositoryError
import com.example.spotiplus.data.repository.RepositoryResponse
import com.example.spotiplus.data.repository.ResponseListener

class MoviesViewModel(private val repository: MoviesRepository):ViewModel() {

    val movies = MutableLiveData<List<Movies>>(null)
    val errorMessage = MutableLiveData<String>(null)
    fun loadMovies(){
        repository.getPopular(object : ResponseListener<PopularMoviesResponse> {
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
        repository.getMovieById(object : ResponseListener<MovieByIdResponse> {

            override fun onResponse(response: RepositoryResponse<MovieByIdResponse>) {
                movieById.value = response.data
            }

            override fun onError(repositoryError: RepositoryError) {
                errorMessage.value = repositoryError.message
            }
        },id)
    }
}