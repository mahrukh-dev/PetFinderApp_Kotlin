package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.AnimalViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.TokenViewModel
import com.mahrukhdev.petfinderapp_kotlin.utils.Constants
import com.mahrukhdev.petfinderapp_kotlin.utils.Constants.TOKEN_VALUE


class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var listener: NavController.OnDestinationChangedListener


    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            val tvm = TokenViewModel()
            tvm.getToken { token ->
                if (token != null) {
                    //Log.d("HOME TOKEN VALUE", token ?: "Token data is null")
                } else {
                    // Handle the case when token is null
                    println("Token is null")
                }
            }
            // Schedule the runnable again after the interval
            handler.postDelayed(this, 3600)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.home_navgraph)
        navController.graph = navGraph

        //setting bottom navigation
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navView.setupWithNavController(navController)

        drawerLayout = findViewById(R.id.home_drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.home_navigation_view)
        navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        listener = NavController.OnDestinationChangedListener{ controller, destination, arguments ->


        }



        setupActionBarWithNavController(navController)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.hamburger)
        }

        handler.postDelayed(runnable, 3600)




//        Constants.TOKEN_VALUE.observe(this, Observer {
//            //txtV.text= TOKEN_VALUE.value
//        })
//
//        val vm = AnimalViewModel()
//        vm.getAnimals()
//
//
//        vm.animalListLiveData?.observe(this, Observer {
//            if (it != null) {
//                for (a in it) {
//                    Log.d("Home", a.name)
//                    println(a.name)
//                   // txtV.text = it.first().name
//                }
//            } else {
//                Log.d("HOME", "error")
//            }
    //    }
    //)

    }


    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.home_nav_host)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}
