package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.model.AnimalCallback
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentPetCategoryBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.adapters.GridListAdapter
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.AnimalViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2
import com.mahrukhdev.petfinderapp_kotlin.utils.hideActionBar

class PetCategoryFragment : BaseFragmentV2<FragmentPetCategoryBinding>(R.layout.fragment_pet_category) {


    val args: PetCategoryFragmentArgs by navArgs()

    private val viewModel: AnimalViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = args.categoryName

        viewModel.getAnimalsByType(type, object : AnimalCallback {
            override fun onAnimalsReceived(animalList: List<Animal>) {
                val adapter = GridListAdapter(animalList)
                binding.petCategoryRecyclerView.adapter = adapter
            }
            override fun onError(message: String) {
                // Handle the error here
                Log.d("ANIMAL", message)
            }
        })
    }

   }