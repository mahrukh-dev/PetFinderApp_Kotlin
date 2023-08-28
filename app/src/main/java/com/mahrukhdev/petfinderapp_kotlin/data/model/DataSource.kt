package com.mahrukhdev.petfinderapp_kotlin.data.model

class Datasource {

    var animalList: MutableList<Animal> = mutableListOf()
    fun loadFavAnimals(): MutableList<Animal>{
        return animalList
    }

    fun addAnimals(animals:  List<Animal>){
        animalList.addAll(0, animals)
    }

}