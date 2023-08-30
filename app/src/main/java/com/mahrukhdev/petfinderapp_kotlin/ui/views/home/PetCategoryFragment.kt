package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.model.AnimalCallback
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentPetCategoryBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.adapters.GridListAdapter
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.AnimalViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2

class PetCategoryFragment : BaseFragmentV2<FragmentPetCategoryBinding>(R.layout.fragment_pet_category) {


    val args: PetCategoryFragmentArgs by navArgs()

    private val viewModel: AnimalViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar3.visibility =View.VISIBLE
        val type = args.categoryName

        viewModel.getAnimalsByType(type, object : AnimalCallback {
            override fun onAnimalsReceived(animalList: List<Animal>) {
                binding.progressBar3.visibility =View.GONE
                val adapter = GridListAdapter(animalList) {}
                binding.petCategoryRecyclerView.adapter = adapter
            }
            override fun onError(message: String) {
                binding.progressBar3.visibility =View.VISIBLE
                // Handle the error here
                Log.d("ANIMAL", message)
            }
        })
    }

   }