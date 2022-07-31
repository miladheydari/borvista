package com.borvista.data.source

import com.borvista.data.models.CharacterEntity
import com.borvista.data.repository.CharacterDataSource
import com.borvista.data.repository.CharacterRemote
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterRemote: CharacterRemote
) : CharacterDataSource {

    override suspend fun getCharacters(query: String): List<CharacterEntity> {
        return characterRemote.getCharacters(query)
    }
}
