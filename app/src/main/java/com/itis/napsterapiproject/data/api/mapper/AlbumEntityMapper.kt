package com.itis.napsterapiproject.data.api.mapper

import com.itis.napsterapiproject.data.api.model.AlbumResponse
import com.itis.napsterapiproject.data.api.model.AlbumsListResponse
import com.itis.napsterapiproject.domain.entity.AlbumEntity
import javax.inject.Inject

class AlbumEntityMapper @Inject constructor() {

    fun mapToAlbum(response: AlbumsListResponse): AlbumEntity =
        mapToAlbumEntity(response.albumResponses[0])

    fun mapToAlbumList(response: AlbumsListResponse): List<AlbumEntity> =
        response.albumResponses.map { item -> mapToAlbumEntity(item) }

    private fun mapToAlbumEntity(albumResponse: AlbumResponse): AlbumEntity = AlbumEntity(
        id = albumResponse.id,
        name = albumResponse.name,
        artistName = albumResponse.artistName,
        released = albumResponse.originallyReleased,
        label = albumResponse.label,
        copyright = albumResponse.copyright,
        trackCount = albumResponse.trackCount,
        imageLink = albumResponse.links.images.href
    )
}
