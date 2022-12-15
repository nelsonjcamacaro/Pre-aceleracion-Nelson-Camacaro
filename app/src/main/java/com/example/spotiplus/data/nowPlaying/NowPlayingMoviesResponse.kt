package com.example.spotiplus.data.nowPlaying

import com.example.spotiplus.data.upcoming.UpcomingMovies
import com.google.gson.annotations.SerializedName
import java.util.*

class NowPlayingMoviesResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movies:List<NowPlayingMovies>,
    @SerializedName("dates")
    val dates: Objects,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int
)
