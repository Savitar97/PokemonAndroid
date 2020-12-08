package com.example.android.pokemon.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.pokemon.dao.PokeListDao
import com.example.android.pokemon.model.PokemonEntity

class DetailsViewModel(private val pokemonID: Long = 0L,
                       dataSource: PokeListDao): ViewModel() {
    val database = dataSource
    private val pokemon: LiveData<PokemonEntity>
    fun getPokemon() = pokemon

    init {
        pokemon=database.getPokemonByID(pokemonID)
    }
}