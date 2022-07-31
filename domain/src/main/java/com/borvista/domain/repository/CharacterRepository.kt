package com.borvista.domain.repository

import com.borvista.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(query: String): Flow<List<Character>>
}
