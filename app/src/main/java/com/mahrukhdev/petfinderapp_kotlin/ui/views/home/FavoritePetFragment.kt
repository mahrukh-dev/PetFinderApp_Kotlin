package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Datasource
import com.mahrukhdev.petfinderapp_kotlin.ui.adapters.FavoriteItemAdapter

class FavoritePetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val animalList = Datasource().loadFavAnimals()
        val recyclerView = view?.findViewById<RecyclerView>(R.id.fav_pet_recycler_view)
        recyclerView?.adapter = FavoriteItemAdapter(this, animalList)
        return inflater.inflate(R.layout.fragment_favorite_pet, container, false)


    }

}