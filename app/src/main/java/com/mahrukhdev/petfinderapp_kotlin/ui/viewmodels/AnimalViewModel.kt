package com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.model.AnimalCallback
import com.mahrukhdev.petfinderapp_kotlin.data.model.Datasource
import com.mahrukhdev.petfinderapp_kotlin.data.repository.AnimalRepository

class AnimalViewModel: ViewModel() {
    private var animalRepository: AnimalRepository? = null
    var animalListLiveData = MutableLiveData<List<Animal>>().apply { value = emptyList() }

    init {
        animalRepository = AnimalRepository()
    }
    fun getAnimals(callback: AnimalCallback) {
        animalRepository?.getAnimals(object : AnimalCallback {
            override fun onAnimalsReceived(animals: List<Animal>) {
                // Handle the list of animals here
                Log.d("ANIMAL", animals.firstOrNull()?.name ?: "No animals")
                callback.onAnimalsReceived(animals)
            }
            override fun onError(message: String) {
                // Handle the error here
                Log.d("ANIMAL", message)
                callback.onError(message)
            }
        })
    }



}