package com.itis.napsterapiproject.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.napsterapiproject.domain.entity.AlbumEntity

class AlbumAdapter(
    private val list: List<AlbumEntity>,
    private val glide: RequestManager,
    private val action: (String) -> Unit
) : RecyclerView.Adapter<AlbumHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumHolder = AlbumHolder.create(parent, glide, action)

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
