package com.mahrukhdev.petfinderapp_kotlin.data.model

data class AnimalResponse(
    val animals: List<Animal>,
    val pagination: Pagination
)