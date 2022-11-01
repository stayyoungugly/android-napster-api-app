package com.itis.napsterapiproject.presentation.presenter.albumList

import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.domain.usecase.GetNewAlbumsUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class AlbumListPresenterTest {
    @MockK
    lateinit var useCase: GetNewAlbumsUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `AlbumListView$$State`

    private lateinit var presenter: AlbumListPresenter

    private val limit = 2

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = spyk(AlbumListPresenter(useCase))
        presenter.setViewState(viewState)
    }

    @Test
    fun onGetAlbumsClickTest() {
        val expectedList = arrayListOf<AlbumEntity>(
            mockk { every { id } returns "alb.667238269" },
            mockk { every { id } returns "alb.666402656" },
        )
        every { useCase.invoke(limit) } returns Single.just(expectedList)

        presenter.onGetAlbumsClick(limit)
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify { viewState.showNewAlbums(expectedList) }
    }

    @Test
    fun onGetAlbumsClickException() {
        val expectedList = arrayListOf<AlbumEntity>(
            mockk { every { id } returns "alb." },
            mockk { every { id } returns "alb.0" },
        )

        val error = mockk<Throwable>()
        every { useCase.invoke(limit) } returns Single.error(error)

        presenter.onGetAlbumsClick(limit)
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        verify(inverse = true) {
            viewState.showNewAlbums(expectedList)
        }
        verify {
            viewState.showError(error)
        }
    }
}
