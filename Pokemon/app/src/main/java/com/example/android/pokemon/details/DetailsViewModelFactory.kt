package com.example.android.pokemon.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.pokemon.dao.PokeListDao

class DetailsViewModelFactory (private val pokemonID: Long,
private val dataSource: PokeListDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(pokemonID, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}