package com.mahrukhdev.petfinderapp_kotlin.data.model
interface AnimalCallback {
    fun onAnimalsReceived(animals: List<Animal>)
    fun onError(message: String)
}
