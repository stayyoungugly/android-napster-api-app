package com.itis.napsterapiproject.data.api.model


import com.google.gson.annotations.SerializedName

data class Discographies(
    @SerializedName("main")
    val main: List<String>,
    @SerializedName("others")
    val others: List<String>
)
