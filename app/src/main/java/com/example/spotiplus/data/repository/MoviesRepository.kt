package com.example.spotiplus.data.repository

import com.example.spotiplus.data.MovieByIdResponse
import com.example.spotiplus.data.MoviesRemoteDataSource
import com.example.spotiplus.data.popular.PopularMoviesResponse

class MoviesRepository(private val remoteDataSource: MoviesRemoteDataSource) {
    fun getPopular(listener: ResponseListener<PopularMoviesResponse>){
        this.remoteDataSource.getPopularMovies(listener)
    }

    fun getMovieById(listener: ResponseListener<MovieByIdResponse>, movieId:Int){
        this.remoteDataSource.getMovieById(listener,movieId)
    }
}