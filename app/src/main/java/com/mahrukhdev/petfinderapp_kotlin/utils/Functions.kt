package com.mahrukhdev.petfinderapp_kotlin.utils

import android.util.Patterns
fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}