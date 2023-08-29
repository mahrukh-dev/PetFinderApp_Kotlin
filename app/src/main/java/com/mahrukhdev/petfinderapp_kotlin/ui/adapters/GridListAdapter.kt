package com.mahrukhdev.petfinderapp_kotlin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.squareup.picasso.Picasso


class GridListAdapter(private val list: List<Animal>) : RecyclerView.Adapter<GridListAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val animalIdTxt: TextView = view.findViewById(R.id.grid_item_id)
        val animalNameTxt: TextView = view.findViewById(R.id.grid_item_name)
        val animalTypeTxt: TextView = view.findViewById(R.id.grid_item_type)
        val animalImg: ImageView = view.findViewById(R.id.grid_item_img)
    }


    //invoked by layout manager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.animal_grid_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = list.size

    //replace content of view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (list.isEmpty()) {
            // Handle the case when the data list is empty
        } else {
            // Bind data to the view holder for the non-empty list
            // Example: holder.bindData(dataList.get(position));

            val item = list[position]
            holder.animalIdTxt.text = item.id.toString()
            holder.animalNameTxt.text = item.name.toString()
            holder.animalTypeTxt.text = item.type.toString()
            if (item.photos.isNotEmpty()) {
                val photoUrl = item.photos.first().medium// Choose the appropriate size here

                Picasso.get()
                    .load(photoUrl)
                    .into(holder.animalImg)
            } else {
                holder.animalImg.setImageResource(R.drawable.default_four)
            }


        }

//        Glide.with(context)
//            .load(photoUrl)
//            .into(holder.animalImg)
    }

}