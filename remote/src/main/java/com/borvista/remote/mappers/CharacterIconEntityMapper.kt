package com.borvista.remote.mappers

import com.borvista.data.models.CharacterIconEntity
import com.borvista.remote.models.CharacterIconModel
import javax.inject.Inject

class CharacterIconEntityMapper @Inject constructor() :
    EntityMapper<CharacterIconModel, CharacterIconEntity> {
    override fun mapFromModel(model: CharacterIconModel): CharacterIconEntity {
        return CharacterIconEntity(url = model.URL)
    }
}
