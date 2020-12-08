package com.example.android.pokemon.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "poke_table")
data class PokemonEntity (
        @SerializedName("id")@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Long = 0,
        @SerializedName("num")@ColumnInfo(name = "number") var num:String="",
        @SerializedName("name")@ColumnInfo(name="pokemon_name") var name:String="",
        @SerializedName("img")@ColumnInfo(name ="img") var img:String?="",
        @Ignore@SerializedName("type") val type:List<String>?=null,
        @SerializedName("height")@ColumnInfo(name ="height") var height:String?="",
        @SerializedName("weight")@ColumnInfo(name="weight") var weight:String?="",
        @SerializedName("candy")@ColumnInfo(name="candy") var candy:String?="",
        @SerializedName("candy_count")@ColumnInfo(name="candy_count") var candy_count:Int?=0,
        @SerializedName("egg")@ColumnInfo(name="egg") var egg:String?="",
        @SerializedName("spawn_chance")@ColumnInfo(name="spawn_chance") var spawn_chance:Double?=0.0,
        @SerializedName("avg_spawns")@ColumnInfo(name="avg_spawns") var avg_spawns:Double?=0.0,
        @SerializedName("spawn_time")@ColumnInfo(name="spawn_time") var spawn_time:String?="",
        @Ignore@SerializedName("multipliers") val multipliers:List<Double>?=null,
        @Ignore@SerializedName("weaknesses") val weaknesses:List<String>?=null,
        @Ignore@SerializedName("next_evolution") val next_evolution:List<Evolution>?=null,
        @Ignore@SerializedName("prev_evolution") val prev_evolution:List<Evolution>?=null

                    )