package com.example.android.pokemon.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.pokemon.R
import com.example.android.pokemon.database.PokeDatabase
import com.example.android.pokemon.databinding.FragmentPokeDexBinding


class PokeDexFragment : Fragment() {
    lateinit var viewModel: PokeDexViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPokeDexBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_poke_dex, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = PokeDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = PokeDexViewModelFactory(dataSource)


        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(PokeDexViewModel::class.java)


        val adapter = this.context?.let { PokeDexRecycleAdapter(it,PokemonClickListener{pokemon->
            viewModel.onItemClicked(pokemon)
        }) }

        val manager = GridLayoutManager(activity, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int =
                when (position) {
                    0 -> 2
                    else -> 1
                }
        }

        binding.pokemonRecycleview.adapter = adapter
        binding.pokemonRecycleview.layoutManager = manager
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter?.addHeaderAndSubmitList(it)
            }
        })


        binding.btnClear.setOnClickListener {
            viewModel.clearPokemon()
        }

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer{pokemon->
            pokemon?.let {
                this.findNavController().navigate(PokeDexFragmentDirections.actionPokedexFragmentToDetailsFragment(pokemon))
                viewModel.onDetailNavigated()
            }
        })
        binding.navigateAdd.setOnClickListener {
            this.findNavController()
                .navigate(PokeDexFragmentDirections.actionPokedexFragmentToAddFragment())
        }

        binding.lifecycleOwner = this
        return binding.root
    }


}

