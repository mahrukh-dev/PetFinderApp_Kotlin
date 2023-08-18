package com.mahrukhdev.petfinderapp_kotlin.ui.views.auth

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignInBinding
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignUpBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.SignUpViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.home.HomeActivity

class SignUp : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        return binding.root
    }

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