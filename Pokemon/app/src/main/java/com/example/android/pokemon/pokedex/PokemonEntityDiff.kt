package com.example.android.pokemon.pokedex

import androidx.recyclerview.widget.DiffUtil
import com.example.android.pokemon.model.PokemonEntity

class PokemonEntityDiff : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }


}