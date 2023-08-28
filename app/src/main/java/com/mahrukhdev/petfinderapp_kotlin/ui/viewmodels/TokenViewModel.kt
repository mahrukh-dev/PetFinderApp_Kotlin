package com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels

import android.util.Log
import com.mahrukhdev.petfinderapp_kotlin.data.repository.TokenRepository
import com.mahrukhdev.petfinderapp_kotlin.utils.Constants

class TokenViewModel {

    private var tokenRepository: TokenRepository? = null
    var tokenData: String? = null

    init {
        tokenRepository = TokenRepository()
    }

    fun getToken(callback: (String?) -> Unit){
        tokenRepository?.getToken { tokenResponse ->
            tokenResponse?.let {

                tokenData = it.access_token

                callback(tokenData)
             //   Log.d("HOME TOKEN VALUE", tokenData ?: "Token data is null")
            }
        }
    }
}
