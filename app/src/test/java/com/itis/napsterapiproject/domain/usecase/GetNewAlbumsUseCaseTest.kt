package com.itis.napsterapiproject.domain.usecase

import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName

class GetNewAlbumsUseCaseTest {

    @MockK
    lateinit var repository: AlbumRepository

    private lateinit var useCase: GetNewAlbumsUseCase

    private val limit = 2

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = spyk(GetNewAlbumsUseCase(repository))
    }

    @Test
    @DisplayName("Successful getting new albums")
    operator fun invoke() {
        val expectedList = arrayListOf<AlbumEntity>(
            mockk { every { id } returns "alb.667238269" },
            mockk { every { id } returns "alb.666402656" },
        )
        every { repository.getNewAlbums(limit) } returns Single.just(expectedList)

        val result = useCase.invoke(limit)

        assertEquals(
            "alb.667238269",
            result.blockingGet()[0].id
        )
        assertEquals(
            "alb.666402656",
            result.blockingGet()[1].id
        )
    }

    @Test
    @DisplayName("Error when try to get new albums")
    fun invokeTestException() {
        val mockError = mockk<Throwable>()
        every {
            repository.getNewAlbums(limit)
        } returns Single.error(mockError)

        val result = useCase.invoke(limit)

        assertEquals(
            mockError,
            result.blockingGet()
        )
    }
}
