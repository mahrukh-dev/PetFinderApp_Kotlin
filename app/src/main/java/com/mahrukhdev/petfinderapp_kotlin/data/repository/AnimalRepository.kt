package com.mahrukhdev.petfinderapp_kotlin.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiInterface

class AnimalRepository {

    private val apiInterface = PetApiClient.getPetApiClient().create(PetApiInterface::class.java)


    fun getAnimals() : MutableLiveData<List<Animal>?>{
        val call = apiInterface.getAnimals(
            "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJSSG1RNmptME44NnNwVFAweHdlN3M2UXN0WFk5ZklWMWhZZldPeVdjWm0wNWZHc3prNSIsImp0aSI6IjYzMWE0MTE0OGNjM2IxZDk5MmQxMzJlNWM0MDY5YmZhNzgwNTRlNzE4NzE1ZDNkZWVjNDZjZTQ0MDBiMWJiZjMyM2ZjMGIzYzFjMDgwYjMxIiwiaWF0IjoxNjkyNjE1MTA3LCJuYmYiOjE2OTI2MTUxMDcsImV4cCI6MTY5MjYxODcwNywic3ViIjoiIiwic2NvcGVzIjpbXX0.qT2nHupE2kHzEMF7Bm-6iLHy4n_UxRcw_63m1pIBBHDqaVl_J31q_a_MeMSr6VbOJ4isEW37Z1lVL_xcvt_JRcN24KFhOoaUZGe1OyXwIfo4hBLEloAfX03fI50rqhKfUuhZTr-LmL2jn8CLmJMtZkroL3joo2KpxipPC33Ou__TSSrVrXTXFRHcUc1qsEqQUpoH8dz9w1QOIcoBE6zDIvur3HB9o_7yVcJxN22GmW9hJGXPzKPHPrx_yHV5gk6q7WCVBXnDIEg1UF38vcIWH5kXQrXEt150yGGnLdmwbUV_26Ed5e7MtVYyfy-kGknICboyJm0aPQzAsXCwZffQIw",  // Replace with your actual access token
        )

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

    fun fetchAllPosts(): MutableLiveData<List<Animal>?> {
        val data = MutableLiveData<List<Animal>?>()


        apiInterface?.fetchAllPosts()?.enqueue(object : Callback<List<Animal>> {

            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<Animal>>,
                response: Response<List<Animal>>
            ) {

                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }

}