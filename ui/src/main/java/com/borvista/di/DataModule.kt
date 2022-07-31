package com.borvista.di

import com.borvista.data.CharacterRepositoryImp
import com.borvista.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(characterRepository: CharacterRepositoryImp): CharacterRepository =
        characterRepository


}
