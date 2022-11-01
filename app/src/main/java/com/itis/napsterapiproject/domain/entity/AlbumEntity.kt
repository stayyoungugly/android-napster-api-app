package com.itis.napsterapiproject.domain.entity

import com.google.gson.annotations.SerializedName

data class AlbumEntity(
    val id: String,
    val name: String,
    val released: String,
    val label: String,
    val copyright: String,
    val trackCount: Int,
    val artistName: String,
    val imageLink: String,
)
