package com.itis.napsterapiproject.data.impl

import com.itis.napsterapiproject.data.api.NapsterApi
import com.itis.napsterapiproject.data.api.mapper.AlbumEntityMapper
import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.domain.repository.AlbumRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val api: NapsterApi,
    private val mapper: AlbumEntityMapper,
) : AlbumRepository {

    override fun getAlbumById(
        id: String
    ): Single<AlbumEntity> = api.getAlbumById(id)
        .map {
            mapper.mapToAlbum(it)
        }

    override fun getNewAlbums(limit: Int): Single<List<AlbumEntity>> = api.getNewAlbums(limit)
        .map {
            mapper.mapToAlbumList(it)
        }
}
