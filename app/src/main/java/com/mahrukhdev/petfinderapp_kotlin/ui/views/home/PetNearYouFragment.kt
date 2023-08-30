package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.model.AnimalCallback
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentPetNearYouBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.adapters.GridListAdapter
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.AnimalViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2

class PetNearYouFragment : BaseFragmentV2<FragmentPetNearYouBinding>(R.layout.fragment_pet_near_you){

    private val viewModel: AnimalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.progressBar.visibility =View.VISIBLE
        viewModel.getAnimals(object : AnimalCallback {
            override fun onAnimalsReceived(animalList: List<Animal>) {
                binding.progressBar.visibility =View.GONE
                val adapter = GridListAdapter(animalList) {singleAnimalPos: Int ->

                        val action = PetNearYouFragmentDirections.actionPetNearYouFragmentToAnimalFragment(
                            "Animal Id: " + animalList[singleAnimalPos].id.toString(),
                            "Animal Name: " + animalList[singleAnimalPos].name,
                            "Animal Type: " + animalList[singleAnimalPos].type,
                            "Animal Gender: " + animalList[singleAnimalPos].gender,
                            "Animal Age: " + animalList[singleAnimalPos].age,
                            "Animal Description: " + animalList[singleAnimalPos].description,
                            animalList[singleAnimalPos].contact.address.address1.toString() + " "
                                    + animalList[singleAnimalPos].contact.address.address2.toString() + " , "
                                    + animalList[singleAnimalPos].contact.address.city.toString() + " , "
                                    + animalList[singleAnimalPos].contact.address.state.toString() + " "
                                    + animalList[singleAnimalPos].contact.address.postcode.toString() + " , "
                                    + animalList[singleAnimalPos].contact.address.country.toString()
                        )
                        findNavController().navigate(action)


                }
                binding.petNearRecyclerView.adapter = adapter
            }

            override fun onError(message: String) {
                binding.progressBar.visibility =View.VISIBLE
                // Handle the error here
                Log.d("ANIMAL", message)
            }
        })

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_drawer_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {

                view?.let { Navigation.findNavController(it).navigate(R.id.action_petNearYouFragment_self) }
//                view?.let { Navigation.findNavController(it).navigate(R.id.action_petCategoryFragment_to_petNearYouFragment) }

            }
            else -> {
                val action = PetNearYouFragmentDirections.actionPetNearYouFragmentToPetCategoryFragment(
                    item.title as String
                )
                view?.let { Navigation.findNavController(it).navigate(action) }
            }
        }
        return true
    }



}


