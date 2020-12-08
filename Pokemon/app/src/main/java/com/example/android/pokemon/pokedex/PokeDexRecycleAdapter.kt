package com.example.android.pokemon.pokedex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.pokemon.R
import com.example.android.pokemon.databinding.PokemonListItemBinding
import com.example.android.pokemon.model.PokemonEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PokeDexRecycleAdapter(private var context: Context, val clickListener: PokemonClickListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(PokemonEntityDiff()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> TextViewHolder.from(parent)
            TYPE_ITEM -> PokemonViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    fun addHeaderAndSubmitList(list: List<PokemonEntity>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.PokemonItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is PokemonViewHolder -> {
                val item = getItem(position) as DataItem.PokemonItem
                holder.bind(item.pokemon, clickListener)
                Glide.with(context).load(item.pokemon.img)
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .into(holder.img_pokemon);
                holder.name_pokemon.text = item.pokemon.name
                print(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_ITEM
    }

    class PokemonViewHolder constructor(val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal var img_pokemon: ImageView = itemView.findViewById(R.id.pokemon_image) as ImageView
        internal var name_pokemon: TextView = itemView.findViewById(R.id.pokemon_name) as TextView
        fun bind(item: PokemonEntity, clickListener: PokemonClickListener) {
            binding.pokemon = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PokemonViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PokemonListItemBinding.inflate(layoutInflater, parent, false)
                return PokemonViewHolder(binding)
            }
        }
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }
}

class PokemonClickListener (val clickListener: (pokemonId: Long) -> Unit) {
    fun onClick(pokemon: PokemonEntity) = clickListener(pokemon.id)
}


sealed class DataItem {
    abstract val id: Long

    data class PokemonItem(val pokemon: PokemonEntity) : DataItem() {
        override val id = pokemon.id
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }
}