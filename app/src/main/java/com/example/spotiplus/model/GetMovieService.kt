package com.example.spotiplus.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey:String):Call<PopularMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") id:String, @Query("api_key") apiKey:String): Call<MovieByIdResponse>
}