package com.itis.napsterapiproject.presentation.presenter.albumDetails

import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.domain.usecase.GetAlbumByIdUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class AlbumDetailsPresenterTest {
    @MockK
    lateinit var useCase: GetAlbumByIdUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `AlbumDetailsView$$State`

    private lateinit var presenter: AlbumDetailsPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = spyk(AlbumDetailsPresenter(useCase))
        presenter.setViewState(viewState)
    }

    @Test
    fun onGetAlbumClickTest() {
        val expectedId = "alb.667388250"
        val expectedName = "Mr. Morale & The Big Steppers"
        val expectedAlbumEntity = mockk<AlbumEntity> { every { name } returns expectedName }
        every { useCase.invoke(expectedId) } returns Single.just(expectedAlbumEntity)

        presenter.onGetAlbumClick(expectedId)

        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify { viewState.showAlbumDetails(expectedAlbumEntity) }
    }

    @Test
    fun onGetAlbumClickException() {
        val expectedId = "alb."
        val error = mockk<Throwable>()
        every { useCase.invoke(expectedId) } returns Single.error(error)

        presenter.onGetAlbumClick(expectedId)
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify(inverse = true) {
            viewState.showAlbumDetails(any())
        }

        verify {
            viewState.showError(error)
        }
    }
}
