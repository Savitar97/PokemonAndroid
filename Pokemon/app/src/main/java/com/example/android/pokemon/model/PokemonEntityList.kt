package com.example.android.pokemon.model

import com.google.gson.annotations.SerializedName

class PokemonEntityList {
    @SerializedName("pokemon")
    var pokemons:List<PokemonEntity>?=null
}