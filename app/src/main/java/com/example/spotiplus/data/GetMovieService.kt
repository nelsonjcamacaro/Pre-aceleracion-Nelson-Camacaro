package com.example.spotiplus.data

import com.example.spotiplus.data.latest.LatestMovies
import com.example.spotiplus.data.nowPlaying.NowPlayingMoviesResponse
import com.example.spotiplus.data.popular.PopularMoviesResponse
import com.example.spotiplus.data.topRated.TopRatedMoviesResponse
import com.example.spotiplus.data.tvSeries.PopularTVSeriesResponse
import com.example.spotiplus.data.upcoming.UpcomingMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey:String):Call<PopularMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") id:String, @Query("api_key") apiKey:String): Call<MovieByIdResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey:String):Call<TopRatedMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcoming(@Query("api_key") apiKey:String):Call<UpcomingMoviesResponse>

    @GET("tv/popular")
    fun getPopularTVSeries(@Query("api_key") apiKey:String):Call<PopularTVSeriesResponse>

    @GET("movie/now_playing")
    fun getNowPlaying(@Query("api_key") apiKey:String):Call<NowPlayingMoviesResponse>
}