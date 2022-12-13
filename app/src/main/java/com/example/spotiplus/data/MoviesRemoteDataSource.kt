package com.example.spotiplus.data

import com.example.spotiplus.BuildConfig
import com.example.spotiplus.data.popular.PopularMoviesResponse
import com.example.spotiplus.data.repository.RepositoryError
import com.example.spotiplus.data.repository.RepositoryResponse
import com.example.spotiplus.data.repository.ResponseListener
import com.example.spotiplus.data.topRated.TopRatedMovies
import com.example.spotiplus.data.topRated.TopRatedMoviesResponse
import retrofit2.*

class MoviesRemoteDataSource {

    fun getPopularMovies(listener: ResponseListener<PopularMoviesResponse>) {
        val service = RetrofitService.instance
            .create(GetMovieService::class.java)
            .getPopularMovies(BuildConfig.API_KEY)

        service.enqueue(object :Callback<PopularMoviesResponse>{
            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                val popularMovies = response.body()
                if (response.isSuccessful && popularMovies != null){
                    listener.onResponse(
                        RepositoryResponse(
                        data = popularMovies
                    )
                    )
                }else{
                    listener.onError(
                        RepositoryError(
                            message = "Su solicitud ha sido rechazada por el servidor",
                            code = response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Vaya, parece que algo inesperado ha pasado",
                        code = -1
                    )
                )
            }

        })
    }

    fun getMovieById(listener: ResponseListener<MovieByIdResponse>, movieId:Int ) {
        val service = RetrofitService.instance
            .create(GetMovieService::class.java)
            .getMovieById(movieId.toString(),BuildConfig.API_KEY)

        service.enqueue(object :Callback<MovieByIdResponse>{
            override fun onResponse(
                call: Call<MovieByIdResponse>,
                response: Response<MovieByIdResponse>
            ) {
                val movieById = response.body()
                if (response.isSuccessful && movieById != null){
                    listener.onResponse(
                        RepositoryResponse(
                            data = movieById
                        )
                    )
            }else{
                    listener.onError(
                        RepositoryError(
                            message = "Su solicitud ha sido rechazada por el servidor",
                            code = response.code()
                        )
                    )

                }
            }

            override fun onFailure(call: Call<MovieByIdResponse>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Vaya, parece que algo inesperado ha pasado",
                        code = -1
                    )
                )
            }

        })
    }

    suspend fun getTopRatedMovies(): TopRatedMoviesResponse?{
        val service = RetrofitService.instance.create(GetMovieService::class.java).getTopRatedMovies(BuildConfig.API_KEY)

        return try {
            val response: Response<TopRatedMoviesResponse> = service.awaitResponse()
            val topRatedMoviesResponse = response.body()
            topRatedMoviesResponse
        }catch (e:Exception){
            null
        }

    }

    suspend fun getUpcoming():UpcomingMoviesResponse?{
        val service = RetrofitService.instance.create(GetMovieService::class.java).getUpcoming(BuildConfig.API_KEY)

        return try {
            val response: Response<UpcomingMoviesResponse> = service.awaitResponse()
            val upcomingMoviesResponse = response.body()
            upcomingMoviesResponse
        }catch (e:Exception){
            null
        }
    }

    suspend fun popularTVSeries():PopularTVSeriesResponse?{
        val service = RetrofitService.instance.create(GetMovieService::class.java).getPopularTVSeries(BuildConfig.API_KEY)

        return try {
            val response: Response<PopularTVSeriesResponse> = service.awaitResponse()
            val popularTvSeries = response.body()
            popularTvSeries
        }catch (e:Exception){
            null
        }
    }
}