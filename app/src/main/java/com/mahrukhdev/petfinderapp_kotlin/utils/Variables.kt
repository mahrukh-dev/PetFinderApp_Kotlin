package com.mahrukhdev.petfinderapp_kotlin.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Constants {
    private val _TOKEN_VALUE = MutableLiveData<String>()

    val TOKEN_VALUE: LiveData<String>
        get() = _TOKEN_VALUE

    fun updateTOKEN_VALUE(newData: String) {
        _TOKEN_VALUE.value = newData
    }
}