package com.itis.napsterapiproject.data.api.model


import com.google.gson.annotations.SerializedName

data class Reviews(
    @SerializedName("href")
    val href: String,
    @SerializedName("ids")
    val ids: List<String>
)
