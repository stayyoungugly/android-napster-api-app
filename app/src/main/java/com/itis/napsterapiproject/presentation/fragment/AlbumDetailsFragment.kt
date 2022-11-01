package com.itis.napsterapiproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.napsterapiproject.R
import com.itis.napsterapiproject.databinding.FragmentAlbumDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment(R.layout.fragment_album_details) {
    private lateinit var binding: FragmentAlbumDetailsBinding

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumDetailsBinding.bind(view)
    }

}
