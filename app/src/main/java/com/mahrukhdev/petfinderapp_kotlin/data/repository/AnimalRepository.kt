package com.mahrukhdev.petfinderapp_kotlin.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.model.Type
import com.mahrukhdev.petfinderapp_kotlin.data.model.TypeX
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiInterface
import com.mahrukhdev.petfinderapp_kotlin.utils.Constants

class AnimalRepository {

    private val apiInterface = PetApiClient.getPetApiClient().create(PetApiInterface::class.java)

    fun getAnimals() : MutableLiveData<List<Animal>?>{
        val call = apiInterface.getAnimals("Bearer ${Constants.TOKEN_VALUE.value}")

        var data = MutableLiveData<List<Animal>?>()

        call.enqueue(object : Callback<List<Animal>> {
            override fun onResponse(call: Call<List<Animal>>,
                                    response: Response<List<Animal>>) {
                if (response.isSuccessful) {
                    var animals = response.body()
                    data.value = animals
                    Log.d("ANIMAL", animals?.first()?.name ?: "ERRORSS")
                    // Handle the list of animals here
                } else {
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                // Handle network or other errors
            }
        })

        return data
    }

    fun getAnimalById(animalID: String): MutableLiveData<Animal?>{

        val call = apiInterface.getAnimalById("Bearer ${Constants.TOKEN_VALUE.value}", animalID)

        var data = MutableLiveData<Animal?>()

        call.enqueue(object : Callback<Animal> {
            override fun onResponse(call: Call<Animal>,
                                    response: Response<Animal>) {
                if (response.isSuccessful) {
                    var animal = response.body()
                    data.value = animal
                    // Handle the list of animals here
                } else {
                    // Handle error response
                }
            }
            override fun onFailure(call: Call<Animal>, t: Throwable) {
                // Handle network or other errors
            }
        })
        return data
    }

    fun getAnimalTypes(): MutableLiveData<List<TypeX>?> {
        val data = MutableLiveData<List<TypeX>?>()

        apiInterface?.getAnimalTypes("Bearer ${Constants.TOKEN_VALUE.value}" )?.enqueue(object : Callback<List<TypeX>> {
            override fun onFailure(call: Call<List<TypeX>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<List<TypeX>>, response: Response<List<TypeX>>) {
                val res = response.body()
                if (response.isSuccessful && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }




}