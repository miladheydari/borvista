package com.borvista.ui.characterdetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.borvista.BuildConfig
import com.borvista.base.BaseFragment
import com.borvista.databinding.FragmentCharacterDetailBinding
import com.borvista.presentation.viewmodel.BaseViewModel
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, BaseViewModel>() {

    override fun getViewBinding(): FragmentCharacterDetailBinding =
        FragmentCharacterDetailBinding.inflate(layoutInflater)


    private val args: CharacterDetailFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateView()
    }

    private fun updateView() {

        handleLoading(false)

            binding.apply {
                binding.textViewCharacterName.text = args.characterName
                glide.load(BuildConfig.BASE_URL+args.characterImage).into(imageViewCharacter)
                textViewCharacterDescription.text = args.characterDescription

        }

    }

    override val viewModel: BaseViewModel
        get() = TODO("Not need yet")


}
