package com.example.android.pokemon.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.android.pokemon.R
import com.example.android.pokemon.database.PokeDatabase
import com.example.android.pokemon.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false)
        val arguments = DetailsFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val dataSource = PokeDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = DetailsViewModelFactory(arguments.pokemonID, dataSource)
        val detailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.detailsViewModel=detailViewModel
        binding.setLifecycleOwner(this)
        detailViewModel.getPokemon().observe(viewLifecycleOwner,Observer{
            binding.pokemonDetailId.text=it.id.toString()
            Glide.with(this).load(it.img)
                .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(binding.pokemonDetailImage);
            binding.pokemonDetailCandy.text=it.candy
            binding.pokemonDetailCandyCount.text=it.candy_count.toString()
            binding.pokemonDetailEgg.text=it.egg
        })
        return binding.root
    }


}
