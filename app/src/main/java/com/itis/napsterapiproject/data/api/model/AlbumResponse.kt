package com.itis.napsterapiproject.data.api.model


import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("accountPartner")
    val accountPartner: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("contributingArtists")
    val contributingArtists: ContributingArtists,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("discCount")
    val discCount: Int,
    @SerializedName("discographies")
    val discographies: Discographies,
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("isAvailableInAtmos")
    val isAvailableInAtmos: Boolean,
    @SerializedName("isAvailableInHiRes")
    val isAvailableInHiRes: Boolean,
    @SerializedName("isExplicit")
    val isExplicit: Boolean,
    @SerializedName("isSingle")
    val isSingle: Boolean,
    @SerializedName("isStreamable")
    val isStreamable: Boolean,
    @SerializedName("label")
    val label: String,
    @SerializedName("links")
    val links: Links,
    @SerializedName("name")
    val name: String,
    @SerializedName("originallyReleased")
    val originallyReleased: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("shortcut")
    val shortcut: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("upc")
    val upc: String
)
