package com.itis.napsterapiproject.di.modules

import com.itis.napsterapiproject.data.impl.AlbumRepositoryImpl
import com.itis.napsterapiproject.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    abstract fun albumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository
}
