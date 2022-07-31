package com.borvista.data.repository

import com.borvista.data.models.CharacterEntity

interface CharacterRemote {
    suspend fun getCharacters(query: String): List<CharacterEntity>
}
