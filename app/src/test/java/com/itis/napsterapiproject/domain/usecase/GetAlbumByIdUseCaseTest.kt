package com.itis.napsterapiproject.domain.usecase

import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith

class GetAlbumByIdUseCaseTest {
    @MockK
    lateinit var repository: AlbumRepository

    private lateinit var useCase: GetAlbumByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = spyk(GetAlbumByIdUseCase(repository))
    }

    @Test
    @DisplayName("Successful getting album name by his ID")
    operator fun invoke() {
        val expectedId = "alb.667388250"
        val expectedName = "Mr. Morale & The Big Steppers"
        val expectedAlbumEntity = mockk<AlbumEntity> { every { name } returns expectedName }
        every { repository.getAlbumById(expectedId) } returns Single.just(expectedAlbumEntity)

        val result = useCase.invoke(expectedId)

        assertEquals(
            expectedName,
            result.blockingGet().name
        )
    }

    @Test
    @DisplayName("Error when try to get album by ID")
    fun invokeTestException() {
        val expectedValue = "alb."
        val mockError = mockk<Throwable>()
        every {
            repository.getAlbumById(expectedValue)
        } returns Single.error(mockError)

        val result = useCase(expectedValue)

        assertEquals(
            mockError,
            result.blockingGet()
        )
    }


}
