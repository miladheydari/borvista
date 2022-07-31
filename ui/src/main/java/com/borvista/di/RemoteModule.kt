package com.borvista.di

import com.borvista.BuildConfig
import com.borvista.data.repository.CharacterRemote
import com.borvista.remote.api.CharacterService
import com.borvista.remote.api.ServiceFactory
import com.borvista.remote.repository.CharacterRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideBlogService(): CharacterService {
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideCharacterRemote(characterRemote: CharacterRemoteImp): CharacterRemote {
        return characterRemote
    }
}
