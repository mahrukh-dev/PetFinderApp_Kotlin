package com.mahrukhdev.petfinderapp_kotlin.ui.views.auth

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Users
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignInBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.SignInViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2
import com.mahrukhdev.petfinderapp_kotlin.ui.views.home.HomeActivity
import com.mahrukhdev.petfinderapp_kotlin.utils.hideActionBar
import com.mahrukhdev.petfinderapp_kotlin.utils.isValidEmail

class SignInFragment : BaseFragmentV2<FragmentSignInBinding>(R.layout.fragment_sign_in) {

    private val viewModel: SignInViewModel by viewModels()

    private lateinit var auth : FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.viewModel = viewModel
        binding.signinBtnSignin.setOnClickListener {
            onSignIn()
        }
        binding.signinTextSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_signUp)
        }
    }

    override fun onResume() {
        super.onResume()
        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.signInConstraintLayout.background.alpha = 120
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.signInConstraintLayout.background.alpha = 255
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                binding.signInConstraintLayout.background.alpha = 255
            }
        }
    }

    fun onSignIn(){
        var email = binding.signinEditTextEmail.text.toString()
        var pass = binding.signinEditTextPassword.text.toString()
        if (email.isEmpty() || pass.isEmpty()){
            if (email.isEmpty()) {
                binding.signinEditTextEmail.error = "Enter your email"
            }
            if (pass.isEmpty()) {
                binding.signinEditTextPassword.error = "Enter a password"
            }
            Toast.makeText(context, "Enter Valid Details", Toast.LENGTH_SHORT).show()
        }
        else if(!isValidEmail(email)){
            binding.signinEditTextEmail.error = "Enter Valid Email Address"
            Toast.makeText(context, "Enter Valid Email Address", Toast.LENGTH_SHORT).show()
        }
        else {
            //authenticate and navigate to sign in

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, HomeActivity::class.java)
                    startActivity(intent)

                } else {
                    // Sign-up failed
                    Toast.makeText(context, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }




}