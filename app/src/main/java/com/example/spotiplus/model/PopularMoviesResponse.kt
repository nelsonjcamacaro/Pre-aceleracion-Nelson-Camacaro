package com.example.spotiplus.model

import com.google.gson.annotations.SerializedName



class PopularMoviesResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val movies:List<Movies>
)



