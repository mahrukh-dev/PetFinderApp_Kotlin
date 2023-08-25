package com.mahrukhdev.petfinderapp_kotlin.ui.views.auth

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignInBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.SignInViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2
import com.mahrukhdev.petfinderapp_kotlin.ui.views.home.HomeActivity
import com.mahrukhdev.petfinderapp_kotlin.utils.hideActionBar

class SignInFragment : BaseFragmentV2<FragmentSignInBinding>(R.layout.fragment_sign_in) {

    private val viewModel: SignInViewModel by viewModels()

    private lateinit var auth : FirebaseAuth

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

    override fun onResume() {
        super.onResume()
        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.signInLayout.background.alpha = 120
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.signInLayout.background.alpha = 255
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                binding.signInLayout.background.alpha = 255
            }
        }
    }




}