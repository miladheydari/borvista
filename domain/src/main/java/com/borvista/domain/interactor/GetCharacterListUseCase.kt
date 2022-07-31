package com.borvista.domain.interactor

import com.borvista.domain.models.Character
import com.borvista.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetCharacterListBaseUseCase = BaseUseCase<String, Flow<List<Character>>>

class GetCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharacterListBaseUseCase {

    override suspend operator fun invoke(query: String) = characterRepository.getCharacters(query)
}
