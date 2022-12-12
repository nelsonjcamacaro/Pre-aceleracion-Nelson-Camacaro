package com.example.spotiplus.data.popular

import com.google.gson.annotations.SerializedName

data class Movies(@SerializedName("id") val id:Int,
                  @SerializedName("title") val title:String,
                  @SerializedName("poster_path") val poster:String,
                  @SerializedName("vote_average") val average:Double,
                  @SerializedName("overview") val overview:String,
                  @SerializedName("release_date") val date:String,
                  @SerializedName("vote_count") val vote_count: Int,
                  @SerializedName("backdrop_path") val backdrop_path:String,
                  @SerializedName("original_language") val original_language:String)
