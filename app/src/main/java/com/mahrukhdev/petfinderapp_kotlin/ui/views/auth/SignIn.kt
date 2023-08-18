package com.mahrukhdev.petfinderapp_kotlin.ui.views.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignInBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.SignInViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.home.HomeActivity

class SignIn : Fragment() {


    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signinViewmodel = viewModel
        binding.signinBtnSignin.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
        }
        binding.signinTextSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_signUp)
        }
    }




}