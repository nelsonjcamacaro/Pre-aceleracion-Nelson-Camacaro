package com.example.spotiplus.data.repository

import com.example.spotiplus.data.MovieByIdResponse
import com.example.spotiplus.data.MoviesRemoteDataSource
import com.example.spotiplus.data.PopularTVSeriesResponse
import com.example.spotiplus.data.UpcomingMoviesResponse
import com.example.spotiplus.data.popular.PopularMoviesResponse
import com.example.spotiplus.data.topRated.TopRatedMoviesResponse

class MoviesRepository(private val remoteDataSource: MoviesRemoteDataSource) {
    fun getPopular(listener: ResponseListener<PopularMoviesResponse>){
        this.remoteDataSource.getPopularMovies(listener)
    }

    fun getMovieById(listener: ResponseListener<MovieByIdResponse>, movieId:Int){
        this.remoteDataSource.getMovieById(listener,movieId)
    }

    suspend fun getTopRatedMovies():TopRatedMoviesResponse?{
        return remoteDataSource.getTopRatedMovies()
    }

    suspend fun getUpcoming():UpcomingMoviesResponse?{
        return remoteDataSource.getUpcoming()
    }

    suspend fun popularTVSeries():PopularTVSeriesResponse?{
        return remoteDataSource.popularTVSeries()
    }
}