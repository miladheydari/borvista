package com.borvista.data.source

import com.borvista.data.repository.CharacterDataSource
import javax.inject.Inject

open class CharacterDataSourceFactory @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) {

    open suspend fun getDataStore(): CharacterDataSource {
        return remoteDataSource
    }
}
