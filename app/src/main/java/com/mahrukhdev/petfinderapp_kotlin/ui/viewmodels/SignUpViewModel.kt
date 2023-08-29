package com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel(){
    val email = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val cpassword = MutableLiveData<String>()

}