package com.itis.napsterapiproject.presentation.presenter.albumList

import com.itis.napsterapiproject.domain.usecase.GetNewAlbumsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class AlbumListPresenter @Inject constructor(
    private val getNewAlbumsUseCase: GetNewAlbumsUseCase,
) : MvpPresenter<AlbumListView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun attachView(view: AlbumListView?) {
        super.attachView(view)
    }

    override fun detachView(view: AlbumListView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun onGetAlbumsClick(limit: Int) {
        disposables += getNewAlbumsUseCase(limit)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showNewAlbums(it)
            }, onError = {
                viewState.showError(it)
            })
    }
}
