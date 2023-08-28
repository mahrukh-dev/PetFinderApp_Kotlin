package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignUpBinding
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentUserProfileBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.views.auth.LoginActivity
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2

class UserProfileFragment : BaseFragmentV2<FragmentUserProfileBinding>(R.layout.fragment_user_profile){


    val database = FirebaseDatabase.getInstance()

    // Reference to the "users" node
    val usersRef = database.getReference("users")

    // User ID for the user you want to retrieve data for
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    // Add a listener to retrieve data for the user
    val userListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Check if the user exists
            if (dataSnapshot.hasChild(userId!!)) {
                val userDataSnapshot = dataSnapshot.child(userId)
                val email = userDataSnapshot.child("email").getValue(String::class.java)
                val name = userDataSnapshot.child("name").getValue(String::class.java)
                val username = name + "101"
                binding.userProfileEmailTxt.text = email
                binding.userProfileNameTxt.text = name
                binding.userProfileUsernameTxt.text = username

                // Do something with the retrieved data
                // For example, update UI elements
            } else {
                // User not found
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle errors
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersRef.addListenerForSingleValueEvent(userListener)



        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        binding.userLogoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}