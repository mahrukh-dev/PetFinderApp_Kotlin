package com.mahrukhdev.petfinderapp_kotlin.data.model

data class Breeds(
    val mixed: Boolean,
    val primary: String,
    val secondary: Any,
    val unknown: Boolean
)