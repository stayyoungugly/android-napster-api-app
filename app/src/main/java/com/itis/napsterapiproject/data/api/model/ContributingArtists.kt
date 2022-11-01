package com.itis.napsterapiproject.data.api.model


import com.google.gson.annotations.SerializedName

data class ContributingArtists(
    @SerializedName("primaryArtist")
    val primaryArtist: String
)
