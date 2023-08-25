package com.mahrukhdev.petfinderapp_kotlin.data.model

import android.media.Image

data class Users(
    var name : String? = null,
    var email : String? = null,
    var uid : String? = null,
    var uimage: Image? = null
)