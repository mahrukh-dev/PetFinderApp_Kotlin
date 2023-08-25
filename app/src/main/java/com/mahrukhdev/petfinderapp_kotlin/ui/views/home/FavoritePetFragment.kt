package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Datasource
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentFavoritePetBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.adapters.FavoriteItemAdapter
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2

class FavoritePetFragment : BaseFragmentV2<FragmentFavoritePetBinding>(R.layout.fragment_favorite_pet) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val animalList = Datasource().loadFavAnimals()
        binding.favPetRecyclerView.adapter =
            this.context?.let { FavoriteItemAdapter(it, animalList) }
    }
}