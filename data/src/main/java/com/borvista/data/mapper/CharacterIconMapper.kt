package com.borvista.data.mapper

import com.borvista.data.models.CharacterIconEntity
import javax.inject.Inject

class CharacterIconMapper @Inject constructor() : Mapper<CharacterIconEntity, String> {
    override fun mapFromEntity(type: CharacterIconEntity): String {
        return type.url
    }

    override fun mapToEntity(type: String): CharacterIconEntity {
        return CharacterIconEntity(type)
    }


}
