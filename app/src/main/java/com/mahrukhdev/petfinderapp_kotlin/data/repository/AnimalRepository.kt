package com.mahrukhdev.petfinderapp_kotlin.data.repository

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.model.AnimalCallback
import com.mahrukhdev.petfinderapp_kotlin.data.model.AnimalResponse
import com.mahrukhdev.petfinderapp_kotlin.data.model.Type
import com.mahrukhdev.petfinderapp_kotlin.data.model.TypeX
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiInterface
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.TokenViewModel
import com.mahrukhdev.petfinderapp_kotlin.utils.Constants

class AnimalRepository {

    private val apiInterface = PetApiClient.getPetApiClient().create(PetApiInterface::class.java)

    fun getAnimals(callback: AnimalCallback) {
        var call: Call<AnimalResponse> = apiInterface.getAnimals("")

        var data = MutableLiveData<List<Animal>>()
        val tvm = TokenViewModel()
        tvm.getToken { token ->
            // Use the token here
            if (token != null) {
                call= apiInterface.getAnimals("Bearer ${token}")
                Log.d("HOME TOKEN VALUE", token ?: "Token data is null")

                call.enqueue(object : Callback<AnimalResponse> {
                    override fun onResponse(call: Call<AnimalResponse>, response: Response<AnimalResponse>) {
                        if (response.isSuccessful) {
                            var animalResponse = response.body()
                            data.value = animalResponse!!.animals
                            callback.onAnimalsReceived(animalResponse!!.animals)
                            Log.d("ANIMAL", data.value!!.first().name ?: "ERRORSS")
                            // Handle the list of animals here
                        } else {
                            // Handle error response
                            Log.d("ANIMAL", "ERRORSS ONLYY")
                            callback.onError("Error Getting Animals")
                        }
                    }

                    override fun onFailure(call: Call<AnimalResponse>, t: Throwable) {
                        // Handle network or other errors
                        val errorMessage = "Network Error: ${t.localizedMessage}"
                        Log.d("ANIMAL", errorMessage)
                        Log.d("ANIMAL", "ON FAILURE ERRORSS")
                        callback.onError(errorMessage)
                    }
                })


            } else {
                // Handle the case when token is null
                println("Token is null")
            }
        }

    }

    fun getAnimalsByType(type: String, callback: (List<Animal>?, String?) -> Unit) {
        val tvm = TokenViewModel()
        tvm.getToken { token ->
            if (token != null) {
                val call: Call<AnimalResponse> = apiInterface.getAnimalsByType("Bearer $token", type)
                call.enqueue(object : Callback<AnimalResponse> {
                    override fun onResponse(call: Call<AnimalResponse>, response: Response<AnimalResponse>) {
                        if (response.isSuccessful) {
                            val animalResponse = response.body()
                            val animals = animalResponse?.animals

                            Log.d("ANIMAL TYPE", animals!!.first().name ?: "ERRORSS")
                            callback(animals, null)

                        } else {

                            Log.d("ANIMAL TYPE", "ERRORSS")
                            callback(null, "Unsuccessful response: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<AnimalResponse>, t: Throwable) {
                        val errorMessage = "Network Error: ${t.localizedMessage}"

                        Log.d("ANIMAL TYPE", errorMessage)
                        callback(null, errorMessage)
                    }
                })
            } else {
                callback(null, "Token is null")
            }
        }
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