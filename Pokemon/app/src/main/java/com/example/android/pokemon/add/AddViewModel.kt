package com.example.android.pokemon.add

import androidx.lifecycle.ViewModel
import com.example.android.pokemon.dao.PokeListDao
import com.example.android.pokemon.model.PokemonEntity

class AddViewModel(dataSource: PokeListDao): ViewModel() {
    private val database = dataSource

    fun addPokemmon(number:String,name:String,img:String,height:String,weight:String,candy:String,candy_count:Int,egg:String,spawn_chance:Double,avg_spawns:Double,spawn_time:String){
        val pokemon=PokemonEntity(num=number,name=name,img=img,height = height,weight=weight,candy = candy,candy_count = candy_count,egg = egg,spawn_chance = spawn_chance,avg_spawns = avg_spawns,spawn_time=spawn_time)
        database.insert(pokemon)
    }

}