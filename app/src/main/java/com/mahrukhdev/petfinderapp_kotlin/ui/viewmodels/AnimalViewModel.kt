package com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.repository.AnimalRepository

class AnimalViewModel {
    private var animalRepository: AnimalRepository? = null
    var animalListLiveData = MutableLiveData<List<Animal>>().apply { value = emptyList() }

    init {
        animalRepository = AnimalRepository()
    }

    fun getAnimals() {
        animalRepository?.getAnimals()?.observeForever {
            animalListLiveData?.value = it
        }
    }



}