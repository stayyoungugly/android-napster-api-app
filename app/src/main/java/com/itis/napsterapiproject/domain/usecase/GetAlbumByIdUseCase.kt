package com.itis.napsterapiproject.domain.usecase

import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.domain.repository.AlbumRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetAlbumByIdUseCase @Inject constructor(
    private val repository: AlbumRepository
) {
    operator fun invoke(id: String): Single<AlbumEntity> =
        repository.getAlbumById(id)
            .subscribeOn(Schedulers.io())
}
