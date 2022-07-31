package com.borvista.ui.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.borvista.base.BaseAdapter
import com.borvista.databinding.ItemCharacterListBinding
import com.borvista.domain.models.Character
import javax.inject.Inject

class CharacterAdapter @Inject constructor() : BaseAdapter<Character>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override val differ = AsyncListDiffer(this, diffCallback)

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterListBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Character> {
        override fun bind(item: Character) {
            binding.apply {
                textViewCharacterName.text = item.name
                root.setOnClickListener {
                    onItemClickListener?.let { itemClick ->
                        itemClick(item)
                    }
                }
            }
        }
    }
}
