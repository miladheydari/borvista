package com.borvista.data.mapper

import com.borvista.data.models.CharacterEntity
import com.borvista.domain.models.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor(
    private val iconMapper: CharacterIconMapper
) : Mapper<CharacterEntity, Character> {

    override fun mapFromEntity(type: CharacterEntity): Character {
        val name = type.firstUrl.split("/").last().split("?").first().split("_").joinToString(" ")
        return Character(name = name, image = type.icon.url, description = type.text)
    }

    override fun mapToEntity(type: Character): CharacterEntity {

        return CharacterEntity(
            icon = iconMapper.mapToEntity(type.image),
            text = type.description,
            firstUrl = type.name
        )
    }
}
