package com.borvista.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.borvista.domain.interactor.GetCharacterListUseCase
import com.borvista.domain.models.Character
import com.borvista.presentation.utils.CoroutineContextProvider
import com.borvista.presentation.utils.ExceptionHandler
import com.borvista.presentation.utils.UiAwareLiveData
import com.borvista.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

private const val TAG = "CharacterListViewModel"

sealed class CharacterUIModel : UiAwareModel() {
    object Loading : CharacterUIModel()
    data class Error(var error: Int = 0) : CharacterUIModel()
    data class Success(val data: List<Character>) : CharacterUIModel()
}

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val characterListUseCase: GetCharacterListUseCase,
) : BaseViewModel(contextProvider) {

    private val _characterList = UiAwareLiveData<CharacterUIModel>()
    private var characterList: LiveData<CharacterUIModel> = _characterList
    private lateinit var allCharacters: List<Character>
    private var dataFetched = false
    fun getCharacters(): LiveData<CharacterUIModel> {
        return characterList
    }

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _characterList.postValue(CharacterUIModel.Error(message))
    }

    fun fetchCharacters(query: String) {
        if (dataFetched) {
            _characterList.postValue(CharacterUIModel.Success(allCharacters))
            return
        }

        _characterList.postValue(CharacterUIModel.Loading)
         launchCoroutineIO {
                loadCharacters(query)
            }

    }

    private suspend fun loadCharacters(query: String) {
        characterListUseCase(query).collect {
            dataFetched = true
            allCharacters = it
            _characterList.postValue(CharacterUIModel.Success(it))
        }
    }

    fun search(text: CharSequence?) {


        val filtred = text?.let { searched ->
            allCharacters.filter { character ->

                character.name.toLowerCase().contains(
                    searched.toString().toLowerCase()
                ) || character.description.toLowerCase()
                    .contains(searched.toString().toLowerCase())
            }
        } ?: allCharacters
        _characterList.postValue(CharacterUIModel.Success(filtred))


    }

}
