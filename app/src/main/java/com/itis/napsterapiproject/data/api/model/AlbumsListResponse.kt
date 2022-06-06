package com.itis.napsterapiproject.data.api.model


import com.google.gson.annotations.SerializedName

data class AlbumsListResponse(
    @SerializedName("albums")
    val albumResponses: List<AlbumResponse>
)
