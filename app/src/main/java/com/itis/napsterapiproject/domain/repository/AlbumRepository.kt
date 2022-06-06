package com.itis.napsterapiproject.domain.repository

import com.itis.napsterapiproject.domain.entity.AlbumEntity
import io.reactivex.rxjava3.core.Single

interface AlbumRepository {

    fun getAlbumById(id: String): Single<AlbumEntity>

    fun getNewAlbums(limit: Int): Single<List<AlbumEntity>>

}
