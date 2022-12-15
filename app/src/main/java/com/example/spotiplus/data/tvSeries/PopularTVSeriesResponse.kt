package com.example.spotiplus.data.tvSeries

import com.google.gson.annotations.SerializedName

class PopularTVSeriesResponse (
    @SerializedName("page")
    val page:Int,
    @SerializedName("results")
    val series:List<PopularTvSeries>,
    @SerializedName("total_results")
    val total_results: Int,
    @SerializedName("total_pages")
    val total_pages: Int
)
