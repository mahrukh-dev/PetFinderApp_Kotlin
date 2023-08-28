package com.mahrukhdev.petfinderapp_kotlin.ui.views.auth

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.data.model.Users
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignUpBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.SignUpViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2
import com.mahrukhdev.petfinderapp_kotlin.ui.views.home.HomeActivity
import com.mahrukhdev.petfinderapp_kotlin.utils.hideActionBar

class SignUpFragment : BaseFragmentV2<FragmentSignUpBinding>(R.layout.fragment_sign_up) {


    private val viewModel: SignUpViewModel by viewModels()

    private lateinit var auth : FirebaseAuth
    private lateinit var database : FirebaseDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.signupViewmodel = viewModel

        binding.signupBtnSignup.setOnClickListener {
            var name = binding.signupEditTextName.text.toString()
            var email = binding.signupEditTextEmail.text.toString()
            var pass = binding.signupEditTextPassword.text.toString()
            var cpass = binding.signupEditTextConfirmPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || cpass.isEmpty()){
                if (name.isEmpty()) {
                    binding.signupEditTextName.error = "Enter your name"
                }
                if (email.isEmpty()) {
                    binding.signupEditTextEmail.error = "Enter your email"
                }
                if (pass.isEmpty()) {
                    binding.signupEditTextPassword.error = "Enter a password"
                }
                if (cpass.isEmpty()) {
                    binding.signupEditTextConfirmPassword.error = "Confirm your password"
                }
                Toast.makeText(context, "Enter Valid Details", Toast.LENGTH_SHORT).show()
            }
            else if(!isValidEmail(email)){
                binding.signupEditTextEmail.error = "Enter Valid Email Address"
                Toast.makeText(context, "Enter Valid Email Address", Toast.LENGTH_SHORT).show()
            }
            else if(pass.length < 6){
                binding.signupEditTextPassword.error = "Enter password more than six characters"
                Toast.makeText(context, "Enter password more than six characters", Toast.LENGTH_SHORT).show()
            }
            else if(pass != cpass){
                binding.signupEditTextConfirmPassword.error = "Password not matched, try again"
                Toast.makeText(context, "Password not matched, try again", Toast.LENGTH_SHORT).show()
            }
            else {
                //authenticate and navigate to sign in

                // Create a new user with email and password
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Sign-up successful
                            Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show()
                            //ADD USER DETAILS INTO DATABASE
                            val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                            // You can navigate to the next screen here
                            val user = Users(name, email, auth.currentUser!!.uid)
                            databaseRef.setValue(user).addOnCompleteListener{
                                if(it.isSuccessful){
                                    findNavController().navigate(R.id.action_signUp_to_signIn)
                                }
                                else {
                                    Toast.makeText(context, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            // Sign-up failed
                            Toast.makeText(context, "Something went wrong, try again", Toast.LENGTH_SHORT).show()
                        }
                    }

            }
//
//            startActivity(Intent(context, HomeActivity::class.java))
        }
        binding.signupTextSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }

    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
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