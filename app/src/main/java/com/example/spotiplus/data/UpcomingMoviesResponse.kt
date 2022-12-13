package com.example.spotiplus.data

import com.google.gson.annotations.SerializedName

class UpcomingMoviesResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movies:List<UpcomingMovies>,
    @SerializedName("dates")
    val dates:List<String>,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int
)
