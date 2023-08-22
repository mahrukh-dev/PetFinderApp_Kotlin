package com.mahrukhdev.petfinderapp_kotlin.ui.views.auth

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignUpBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.SignUpViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2
import com.mahrukhdev.petfinderapp_kotlin.ui.views.home.HomeActivity
import com.mahrukhdev.petfinderapp_kotlin.utils.hideActionBar

class SignUpFragment : BaseFragmentV2<FragmentSignUpBinding>(R.layout.fragment_sign_up) {


    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupViewmodel = viewModel

        binding.signupBtnSignup.setOnClickListener {
            startActivity(Intent(context, HomeActivity::class.java))
        }
        binding.signupTextSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }

    }

    override fun onResume() {
        super.onResume()

        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.signUpLayout.background.alpha = 120
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.signUpLayout.background.alpha = 255
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                binding.signUpLayout.background.alpha = 255
            }
        }
    }

}