package com.itis.napsterapiproject.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.itis.napsterapiproject.databinding.ItemAlbumBinding
import com.itis.napsterapiproject.domain.entity.AlbumEntity

class AlbumHolder(
    private val binding: ItemAlbumBinding,
    private val glide: RequestManager,
    private val action: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var album: AlbumEntity? = null

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: AlbumEntity) {
        this.album = item
        with(binding) {
            tvTitle.text = item.name
            tvArtist.text = item.artistName
            glide.load(item.imageLink)
                .apply(options)
                .into(ivCover)
            itemView.setOnClickListener {
                action(item.id)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            action: (String) -> Unit
        ) = AlbumHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action
        )
    }
}
