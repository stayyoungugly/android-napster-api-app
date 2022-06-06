package com.itis.napsterapiproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.itis.napsterapiproject.R
import com.itis.napsterapiproject.databinding.FragmentAlbumListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : Fragment(R.layout.fragment_album_list) {
    private lateinit var binding: FragmentAlbumListBinding

    private val glide by lazy {
        Glide.with(this)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumListBinding.bind(view)
    }

}
