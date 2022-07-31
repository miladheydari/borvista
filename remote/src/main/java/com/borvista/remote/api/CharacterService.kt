package com.borvista.remote.api

import com.borvista.remote.models.CharacterResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/")
    suspend fun getCharacters(@Query("q", encoded=true)q:String,@Query("format")format:String = "json"): CharacterResponseModel

}
