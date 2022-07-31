package com.borvista.data

import com.borvista.data.mapper.CharacterMapper
import com.borvista.data.source.CharacterDataSourceFactory
import com.borvista.domain.models.Character
import com.borvista.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val dataSourceFactory: CharacterDataSourceFactory,
    private val characterMapper: CharacterMapper,
) : CharacterRepository {

    override suspend fun getCharacters(query: String): Flow<List<Character>> = flow {
        val characterList =
            dataSourceFactory.getDataStore().getCharacters(query).map { characterEntity ->
                characterMapper.mapFromEntity(characterEntity)
            }
        emit(characterList)
    }
}
