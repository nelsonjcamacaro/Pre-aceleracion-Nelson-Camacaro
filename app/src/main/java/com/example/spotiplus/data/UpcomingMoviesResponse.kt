package com.example.spotiplus.data

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
