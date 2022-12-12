package com.example.spotiplus.data.popular

import com.google.gson.annotations.SerializedName

class PopularMoviesResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movies:List<Movies>)