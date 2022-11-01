package com.itis.napsterapiproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.itis.napsterapiproject.R
import com.itis.napsterapiproject.databinding.FragmentAlbumListBinding
import com.itis.napsterapiproject.domain.entity.AlbumEntity
import com.itis.napsterapiproject.presentation.presenter.albumList.AlbumListPresenter
import com.itis.napsterapiproject.presentation.presenter.albumList.AlbumListView
import com.itis.napsterapiproject.presentation.recycler.AlbumAdapter
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

const val ALBUMS_AMOUNT = 20

@AndroidEntryPoint
class AlbumListFragment : MvpAppCompatFragment(R.layout.fragment_album_list),
    AlbumListView {
    private lateinit var binding: FragmentAlbumListBinding
    private lateinit var albumAdapter: AlbumAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: AlbumListPresenter

    @ProvidePresenter
    fun providePresenter(): AlbumListPresenter = presenter


    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumListBinding.bind(view)
        val limit = ALBUMS_AMOUNT
        presenter.onGetAlbumsClick(limit)
    }

    override fun showNewAlbums(albums: List<AlbumEntity>) {
        context?.let { context ->
            albumAdapter =
                AlbumAdapter(albums as ArrayList<AlbumEntity>, Glide.with(context)) { album ->
                    moveToAlbumDetails(album)
                }
            binding.rvAlbums.run {
                adapter = albumAdapter
            }
        }
    }

    private fun moveToAlbumDetails(albumId: String) {
        val bundle = Bundle().apply {
            putString("albumId", albumId)
        }
        findNavController().navigate(R.id.action_albumListFragment_to_albumDetailsFragment, bundle)
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
            rvAlbums.isVisible = false
            progressBar.isVisible = true
        }
    }

    override fun hideLoading() {
        with(binding) {
            progressBar.isVisible = false
            rvAlbums.isVisible = true
        }
    }

}
