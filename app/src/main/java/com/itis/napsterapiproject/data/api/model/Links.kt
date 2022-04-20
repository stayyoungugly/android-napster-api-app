package com.itis.napsterapiproject.data.api.model


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("artists")
    val artists: Artists,
    @SerializedName("genres")
    val genres: Genres,
    @SerializedName("images")
    val images: Images,
    @SerializedName("posts")
    val posts: Posts,
    @SerializedName("reviews")
    val reviews: Reviews,
    @SerializedName("tracks")
    val tracks: Tracks
)
