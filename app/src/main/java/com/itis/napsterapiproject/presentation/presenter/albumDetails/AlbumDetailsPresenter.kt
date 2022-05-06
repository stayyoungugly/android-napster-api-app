package com.itis.napsterapiproject.presentation.presenter.albumDetails

import com.itis.napsterapiproject.domain.usecase.GetAlbumByIdUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class AlbumDetailsPresenter @Inject constructor(
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase,
) : MvpPresenter<AlbumDetailsView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun attachView(view: AlbumDetailsView?) {
        super.attachView(view)
    }

    override fun detachView(view: AlbumDetailsView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun onGetAlbumClick(albumId: String) {
        disposables += getAlbumByIdUseCase(albumId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showAlbumDetails(it)
            }, onError = {
                viewState.showError(it)
            })
    }
}
