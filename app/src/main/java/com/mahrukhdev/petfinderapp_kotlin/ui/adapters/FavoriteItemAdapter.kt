package com.mahrukhdev.petfinderapp_kotlin.ui.adapters

import com.bumptech.glide.Glide
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahrukhdev.petfinderapp_kotlin.ui.views.home.FavoritePetFragment

class FavoriteItemAdapter(private val context: FavoritePetFragment, private val list: List<Animal>) : RecyclerView.Adapter<FavoriteItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val animalIdTxt: TextView = view.findViewById(R.id.fav_item_id)
        val animalNameTxt: TextView = view.findViewById(R.id.fav_item_name)
        val animalTypeTxt: TextView = view.findViewById(R.id.fav_item_type)
        val animalDescTxt: TextView = view.findViewById(R.id.fav_item_desc)
        val animalImg: ImageView = view.findViewById(R.id.fav_item_img)
    }


    //invoked by layout manager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = list.size

    //replace content of view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]
        holder.animalIdTxt.text = item.id.toString()
        holder.animalNameTxt.text = item.name.toString()
        holder.animalDescTxt.text = item.description.toString()
        holder.animalTypeTxt.text = item.type.toString()

        val photoUrl = item.photos.first().full // Choose the appropriate size here
        Glide.with(context)
            .load(photoUrl)
            .into(holder.animalImg)
    }

}