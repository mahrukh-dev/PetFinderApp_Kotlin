package com.mahrukhdev.petfinderapp_kotlin.data.repository

import com.mahrukhdev.petfinderapp_kotlin.data.model.TokenResponse
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiClient
import com.mahrukhdev.petfinderapp_kotlin.data.remote.PetApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TokenRepository {

    private val apiService = PetApiClient.getPetApiClient().create(PetApiInterface::class.java)

    fun getToken(callback: (TokenResponse?) -> Unit) {
        val clientId = "RHmQ6jm0N86spTP0xwe7s6QstXY9fIV1hYfWOyWcZm05fGszk5"
        val clientSecret = "WuP6QNSHiEfEsRexSqj7FrWKK4wQ37iKqXB4eSUp"

        val call = apiService.getToken("client_credentials", clientId, clientSecret)

        call?.enqueue(object : Callback<TokenResponse?> {
            override fun onResponse(call: Call<TokenResponse?>, response: Response<TokenResponse?>) {
                if (response.isSuccessful) {
                    val tokenResponse = response.body()
                    callback(tokenResponse)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<TokenResponse?>, t: Throwable) {
                callback(null)
            }
        })
    }
}
