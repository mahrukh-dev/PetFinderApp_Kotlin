package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Datasource
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentPetNearYouBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.adapters.FavoriteItemAdapter
import com.mahrukhdev.petfinderapp_kotlin.ui.adapters.GridListAdapter
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2

class PetNearYouFragment : BaseFragmentV2<FragmentPetNearYouBinding>(R.layout.fragment_pet_near_you) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animalList = Datasource().loadPetsNearYou()
        binding.petNearRecyclerView.adapter = this.context?.let { GridListAdapter(it, animalList) }



    }


}