package com.example.spotiplus.data.latest

import com.example.spotiplus.data.UpcomingMovies
import com.google.gson.annotations.SerializedName
import java.util.*

class LatestMoviesResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movies:List<LatestMovies>,
    @SerializedName("dates")
    val dates: Objects,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int
)