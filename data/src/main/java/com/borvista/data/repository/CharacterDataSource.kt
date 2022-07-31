package com.borvista.data.repository

import com.borvista.data.models.CharacterEntity

interface CharacterDataSource {
    suspend fun getCharacters(query: String): List<CharacterEntity>
}
