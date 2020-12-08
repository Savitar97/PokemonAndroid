package com.example.android.pokemon.retrofit

import com.example.android.pokemon.model.PokemonEntityList
import retrofit2.Call
import retrofit2.http.GET

interface PokemonListEntity {
    @GET("pokedex.json")
    fun getAllPokemonList () : Call<PokemonEntityList>
}