package com.borvista.remote.mappers

import com.borvista.data.models.CharacterEntity
import com.borvista.remote.models.CharacterModel
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor(
    private val characterIconEntityMapper: CharacterIconEntityMapper
) : EntityMapper<CharacterModel, CharacterEntity> {
    override fun mapFromModel(model: CharacterModel): CharacterEntity {
        return CharacterEntity(
            icon = characterIconEntityMapper.mapFromModel(model.icon),
            text = model.text,
            firstUrl = model.firstUrl
        )
    }
}
