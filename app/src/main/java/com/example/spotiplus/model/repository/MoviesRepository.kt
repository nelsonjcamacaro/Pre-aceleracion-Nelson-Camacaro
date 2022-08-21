package com.example.spotiplus.model.repository

import com.example.spotiplus.model.MovieByIdResponse
import com.example.spotiplus.model.MoviesRemoteDataSource
import com.example.spotiplus.model.PopularMoviesResponse
import com.example.spotiplus.model.ResponseListener

class MoviesRepository(private val remoteDataSource: MoviesRemoteDataSource) {
    fun getPopular(listener: ResponseListener<PopularMoviesResponse>){
        this.remoteDataSource.getPopularMovies(listener)
    }

    fun getMovieById(listener: ResponseListener<MovieByIdResponse>,movieId:Int){
        this.remoteDataSource.getMovieById(listener,movieId)
    }
}