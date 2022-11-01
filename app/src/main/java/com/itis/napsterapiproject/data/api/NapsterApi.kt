package com.itis.napsterapiproject.data.api

import com.itis.napsterapiproject.data.api.model.AlbumsListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NapsterApi {
    @GET("albums/{id}")
    fun getAlbumById(@Path("id") id: String): Single<AlbumsListResponse>

    @GET("albums/new")
    fun getNewAlbums(@Query("limit") limit: Int): Single<AlbumsListResponse>

}
