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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignUpBinding
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentUserProfileBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.views.auth.LoginActivity
import com.mahrukhdev.petfinderapp_kotlin.ui.views.base.BaseFragmentV2

class UserProfileFragment : BaseFragmentV2<FragmentUserProfileBinding>(R.layout.fragment_user_profile){


    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val usersRef: DatabaseReference = database.getReference("users")
    val userId: String? = FirebaseAuth.getInstance().currentUser?.uid

    // Add a listener to retrieve data for the user
    private val userListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Check if the user exists
            if (dataSnapshot.hasChild(userId!!)) {
                val userDataSnapshot = dataSnapshot.child(userId)
                val email = userDataSnapshot.child("email").getValue(String::class.java)
                val name = userDataSnapshot.child("name").getValue(String::class.java)
                binding.userProfileEmailTxt.text = email
                binding.userProfileNameTxt.text = name
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