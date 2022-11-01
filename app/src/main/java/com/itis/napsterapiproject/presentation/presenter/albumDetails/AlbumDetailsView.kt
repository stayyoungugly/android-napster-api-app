package com.itis.napsterapiproject.presentation.presenter.albumDetails

import com.itis.napsterapiproject.domain.entity.AlbumEntity
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface AlbumDetailsView : MvpView {
    @Skip
    fun showError(throwable: Throwable)

    fun showLoading()

    fun hideLoading()

    fun showAlbumDetails(album: AlbumEntity)
}
