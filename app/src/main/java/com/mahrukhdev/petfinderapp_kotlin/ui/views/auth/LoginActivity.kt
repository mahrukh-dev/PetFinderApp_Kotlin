package com.mahrukhdev.petfinderapp_kotlin.ui.views.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.utils.hideActionBar

class LoginActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        hideActionBar()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.login_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.login_navgraph)
        navController.graph = navGraph


    }
}