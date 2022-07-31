package com.borvista.remote.repository

import com.borvista.data.models.CharacterEntity
import com.borvista.data.repository.CharacterRemote
import com.borvista.remote.api.CharacterService
import com.borvista.remote.mappers.CharacterEntityMapper
import javax.inject.Inject

class CharacterRemoteImp @Inject constructor(
    private val characterService: CharacterService,
    private val characterEntityMapper: CharacterEntityMapper
) : CharacterRemote {

    override suspend fun getCharacters(query: String): List<CharacterEntity> {
        return characterService.getCharacters(query).characters.map { characterModel ->
            characterEntityMapper.mapFromModel(characterModel)
        }
    }
}
