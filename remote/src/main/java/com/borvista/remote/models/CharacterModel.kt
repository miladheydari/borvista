package com.borvista.remote.models

import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("Icon")
    val icon: CharacterIconModel,
    @SerializedName("Text")
    val text: String,
    @SerializedName("FirstURL")
    val firstUrl: String,
)
