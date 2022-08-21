package com.example.spotiplus.model

import com.google.gson.annotations.SerializedName

class MovieByIdResponse (
    @SerializedName("homepage") val homePage: String,
    @SerializedName("id") val id:Int,
    @SerializedName("overview") val overView:String,
    @SerializedName("poster_path") val poster:String,
    @SerializedName("release_date") val date:String,
    @SerializedName("tagline") val tagLine:String,
    @SerializedName("title") val title:String,
    @SerializedName("vote_average") val average:Double
        )