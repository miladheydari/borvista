package com.borvista.remote.models

import com.google.gson.annotations.SerializedName

data class CharacterResponseModel(
    @SerializedName("RelatedTopics")
    val characters: List<CharacterModel>
)
