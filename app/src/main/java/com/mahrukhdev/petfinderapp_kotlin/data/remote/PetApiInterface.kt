package com.mahrukhdev.petfinderapp_kotlin.data.remote

import com.mahrukhdev.petfinderapp_kotlin.data.model.Animal
import com.mahrukhdev.petfinderapp_kotlin.data.model.TokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

public interface PetApiInterface {

    @FormUrlEncoded
    @POST("oauth2/token")
    fun getToken(
        @Field("grant_type") grantType: String?,
        @Field("client_id") clientId: String?,
        @Field("client_secret") clientSecret: String?
    ): Call<TokenResponse?>?

    @GET("animals")
    fun getAnimals(
        // Add more query parameters here
        @Header("Authorization") authorizationHeader: String
    ): Call<List<Animal>>


    @GET("posts")
    fun fetchAllPosts(): Call<List<Animal>>
}