package com.example.android.pokemon.pokedex

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.android.pokemon.dao.PokeListDao
import com.example.android.pokemon.model.PokemonEntity
import com.example.android.pokemon.model.PokemonEntityList
import com.example.android.pokemon.retrofit.PokemonListEntity
import com.example.android.pokemon.retrofit.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokeDexViewModel(dataSource: PokeListDao): ViewModel() {

    private val database = dataSource

    val pokemons= Transformations.map(database.getAllPokemons()){
        it
    }

    private val _navigateToDetails = MutableLiveData<Long>()
    val navigateToDetails: LiveData<Long>
        get() = _navigateToDetails

    fun onItemClicked(id: Long) {
        _navigateToDetails.value = id
    }

    fun onDetailNavigated() {
        _navigateToDetails.value = null
    }

    fun insert(pokemon:PokemonEntity) {
        database.insert(pokemon)
    }

    private fun clear() {
        database.deleteAll()
    }

    fun clearPokemon(){
        viewModelScope.launch {
            clear()
        }
    }

    init{
        retrofitCreate()
    }

    fun retrofitCreate() {
        val request = RetrofitClient.buildService(PokemonListEntity::class.java)
        val call = request.getAllPokemonList()

        call.enqueue(object : Callback<PokemonEntityList> {
            override fun onResponse(
                call: Call<PokemonEntityList>,
                response: Response<PokemonEntityList>
            ) {
                if (response.isSuccessful) {
                    for (pokemon in response.body()?.pokemons!!) {
                        insert(pokemon)
                    }
                }
            }

            override fun onFailure(call: Call<PokemonEntityList>, t: Throwable) {
                Log.e("RetrofitCall", {t.message}.toString())
            }
        })
    }
}