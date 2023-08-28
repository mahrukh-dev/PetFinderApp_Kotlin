package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignUpBinding
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentUserProfileBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.views.auth.LoginActivity
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2

class UserProfileFragment : BaseFragmentV2<FragmentUserProfileBinding>(R.layout.fragment_user_profile){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        binding.userLogoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}