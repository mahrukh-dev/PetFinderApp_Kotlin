package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
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
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.TokenViewModel


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
        val navView: BottomNavigationView = findViewById(R.id.home_bottom_nav_view)
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

//        var listenerTwo = navigationView.setNavigationItemSelectedListener { menuItem ->
//            // Handle the item click here
//            when (menuItem.itemId) {
//                R.id.homeFragment -> {
//                    navController.navigate(R.id.action_petNearYouFragment_self)
//                    true
//                }
//                else -> {
//                    val action = PetNearYouFragmentDirections.actionPetNearYouFragmentToPetCategoryFragment(
//                        menuItem.title as String
//                    )
//                    navController.navigate(action)
//                    true
//                }
//            }
//        }

       // navController.addOnDestinationChangedListener(listener)

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
