package com.example.spotiplus.data.upcoming

import com.example.spotiplus.data.upcoming.UpcomingMovies
import com.google.gson.annotations.SerializedName
import java.util.Objects

class UpcomingMoviesResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movies:List<UpcomingMovies>,
    @SerializedName("dates")
    val dates:Objects,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int
)
