package com.itis.napsterapiproject.data.api.model


import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("href")
    val href: String,
    @SerializedName("ids")
    val ids: List<String>
)
