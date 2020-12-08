package com.example.android.pokemon.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.pokemon.dao.PokeListDao

class PokeDexViewModelFactory(private val dataSource: PokeListDao) : ViewModelProvider.Factory
{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokeDexViewModel::class.java)) {
            return PokeDexViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}