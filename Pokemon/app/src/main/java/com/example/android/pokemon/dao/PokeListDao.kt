package com.example.android.pokemon.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.pokemon.model.PokemonEntity

@Dao
interface PokeListDao {
    @Transaction
    @Query("SELECT * FROM poke_table ORDER BY id ASC")
    fun getAllPokemons(): LiveData<List<PokemonEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pokemonEntity: PokemonEntity)

    @Transaction
    @Query("DELETE FROM poke_table")
    fun deleteAll()

    @Transaction
    @Query("DELETE FROM poke_table WHERE pokemon_name = :name")
    fun delete(name: String)

    @Transaction
    @Query("SELECT * from poke_table WHERE id = :key")
    fun getPokemonByID(key: Long): LiveData<PokemonEntity>
}