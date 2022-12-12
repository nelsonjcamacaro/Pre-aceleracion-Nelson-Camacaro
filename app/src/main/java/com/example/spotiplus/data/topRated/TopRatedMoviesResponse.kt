package com.example.spotiplus.data.topRated

import com.example.spotiplus.data.topRated.TopRatedMovies
import com.google.gson.annotations.SerializedName

class TopRatedMoviesResponse (
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movies:List<TopRatedMovies>,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    )