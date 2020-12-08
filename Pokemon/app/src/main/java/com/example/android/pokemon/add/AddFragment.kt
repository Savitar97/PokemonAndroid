package com.example.android.pokemon.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.pokemon.R
import com.example.android.pokemon.database.PokeDatabase
import com.example.android.pokemon.databinding.FragmentAddBinding
import com.example.android.pokemon.databinding.FragmentPokeDexBinding
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt


class AddFragment : Fragment() {
    lateinit var viewModel: AddViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = PokeDatabase.getInstance(application).pokemonDatabaseDao
        val viewModelFactory = AddViewModelFactory(dataSource)
        viewModel = ViewModelProvider(
            this, viewModelFactory).get(AddViewModel::class.java)
        binding.submitButton.setOnClickListener(){
            try {
                viewModel.addPokemmon(binding.editTextNum.text.toString(),
                        binding.editTextName.text.toString(),
                        binding.editTextImg.text.toString(),
                        binding.editTextHeight.text.toString(),
                        binding.editTextWeight.text.toString(),
                        binding.editTextCandy.text.toString(),
                        parseInt(binding.editTextCandyCount.text.toString()),
                        binding.editTextEgg.text.toString(),
                        parseDouble(binding.editTextSpawnChance.text.toString()),
                        parseDouble(binding.editTextAvgSpawn.text.toString()),
                        binding.editTextSpawnTime.text.toString()

                )
                this.findNavController().navigate(AddFragmentDirections.actionAddFragmentToPokedexFragment())

            }
            catch(e: Exception){
                e.printStackTrace();
            }
        }


        return binding.root
    }

}