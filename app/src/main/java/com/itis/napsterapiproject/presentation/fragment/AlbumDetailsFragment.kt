package com.itis.napsterapiproject.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.itis.napsterapiproject.R
import com.itis.napsterapiproject.databinding.FragmentAlbumDetailsBinding
import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.presentation.presenter.albumDetails.AlbumDetailsPresenter
import com.itis.napsterapiproject.presentation.presenter.albumDetails.AlbumDetailsView
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class AlbumDetailsFragment : MvpAppCompatFragment(R.layout.fragment_album_details),
    AlbumDetailsView {
    private lateinit var binding: FragmentAlbumDetailsBinding
    private lateinit var glide: RequestManager

    @Inject
    @InjectPresenter
    lateinit var presenter: AlbumDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): AlbumDetailsPresenter = presenter

    private val albumId: String by lazy {
        arguments?.getString("albumId") ?: ""
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumDetailsBinding.bind(view)
        glide = Glide.with(this)
        presenter.onGetAlbumClick(albumId)
    }

    override fun showError(throwable: Throwable) {
        throwable.message?.let {
            Snackbar.make(
                binding.root,
                it,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun showLoading() {
        with(binding) {
            layoutDetails.isVisible = false
            progressBar.isVisible = true
        }
    }

    override fun hideLoading() {
        with(binding) {
            progressBar.isVisible = false
            layoutDetails.isVisible = true
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showAlbumDetails(album: AlbumEntity) {
        with(binding) {
            glide.load(album.imageLink).into(ivCover)
            tvTitle.text = album.name
            tvArtist.text = "by ${album.artistName}"
            tvYear.text = "Was released on ${album.released}"
            tvCopyright.text = "Copyright: ${album.copyright}"
            tvLabel.text = "Label: ${album.label}"
            tvTracks.text = "Tracks: ${album.trackCount}"
        }
    }

}
