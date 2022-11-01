package com.itis.napsterapiproject.domain.entity

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
