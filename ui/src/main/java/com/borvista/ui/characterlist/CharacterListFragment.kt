package com.borvista.ui.characterlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borvista.BuildConfig
import com.borvista.R
import com.borvista.base.BaseFragment
import com.borvista.databinding.FragmentCharacterListBinding
import com.borvista.extension.observe
import com.borvista.presentation.viewmodel.BaseViewModel
import com.borvista.presentation.viewmodel.CharacterListViewModel
import com.borvista.presentation.viewmodel.CharacterUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentCharacterListBinding =
        FragmentCharacterListBinding.inflate(layoutInflater)

    override val viewModel: CharacterListViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCharacters(BuildConfig.QUERY)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.getCharacters(), ::onViewStateChange)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)
        initRecyclerView(binding.recyclerViewCharacters, itemDetailFragmentContainer)
        initSearch(binding.editTextSearch)
    }

    private fun initSearch(
        editTextSearch: AppCompatEditText
    ) {

        editTextSearch.doOnTextChanged { text, _, _, _ ->

            viewModel.search(text)
        }
    }

    private fun initRecyclerView(
        recyclerViewCharacters: RecyclerView,
        itemDetailFragmentContainer: View?
    ) {
        recyclerViewCharacters.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        characterAdapter.setItemClickListener { character ->

            if (itemDetailFragmentContainer != null) {
                val bundle = Bundle().apply {
                    putString(
                        "character_name",
                        character.name
                    )
                    putString(
                        "character_description",
                        character.description
                    )
                    putString(
                        "character_image",
                        character.image
                    )
                }
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {

                findNavController().navigate(
                    CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                        character.name,
                        character.description,
                        character.image
                    )
                )
            }
        }
    }

    private fun onViewStateChange(event: CharacterUIModel) {
        if (event.isRedelivered) return
        when (event) {
            is CharacterUIModel.Error -> handleErrorMessage(event.error)
            is CharacterUIModel.Loading -> handleLoading(true)
            is CharacterUIModel.Success -> {
                handleLoading(false)
                event.data.let {
                    characterAdapter.list = it
                }
            }
        }
    }
}
