package com.mahrukhdev.petfinderapp_kotlin.ui.views.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.AnimalViewModel
import com.mahrukhdev.petfinderapp_kotlin.ui.viewmodels.TokenViewModel
import com.mahrukhdev.petfinderapp_kotlin.utils.Constants
import com.mahrukhdev.petfinderapp_kotlin.utils.Constants.TOKEN_VALUE


class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            val tvm = TokenViewModel()
            tvm.getToken()
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

        setupActionBarWithNavController(navController)

        handler.postDelayed(runnable, 3600)



        Constants.TOKEN_VALUE.observe(this, Observer {
            //txtV.text= TOKEN_VALUE.value
        })

        val vm = AnimalViewModel()
        vm.getAnimals()


        vm.animalListLiveData?.observe(this, Observer {
            if (it != null) {
                for (a in it) {
                    Log.d("Home", a.name)
                    println(a.name)
                   // txtV.text = it.first().name
                }
            } else {
                Log.d("HOME", "error")
            }
        })

    }
}
