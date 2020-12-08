package com.example.android.pokemon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.pokemon.dao.PokeListDao
import com.example.android.pokemon.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 3, exportSchema = false)
abstract class PokeDatabase : RoomDatabase() {

    abstract val pokemonDatabaseDao: PokeListDao

    companion object {

        @Volatile
        private var INSTANCE: PokeDatabase? = null

        fun getInstance(context: Context): PokeDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokeDatabase::class.java,
                        "pokemon_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}