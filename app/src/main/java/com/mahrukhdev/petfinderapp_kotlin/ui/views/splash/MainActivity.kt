package com.mahrukhdev.petfinderapp_kotlin.ui.views.splash

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.mahrukhdev.petfinderapp_kotlin.R
import com.mahrukhdev.petfinderapp_kotlin.databinding.ActivityMainBinding
import com.mahrukhdev.petfinderapp_kotlin.databinding.FragmentSignUpBinding
import com.mahrukhdev.petfinderapp_kotlin.ui.views.auth.LoginActivity
import com.mahrukhdev.petfinderapp_kotlin.utils.hideActionBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        hideActionBar()

        when (this?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.mainLayout.background.alpha = 120
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.mainLayout.background.alpha = 255
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                binding.mainLayout.background.alpha = 255
            }
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        // on below line we are calling
        // handler to run a task
        // for specific time interval
        Handler().postDelayed({
            // on below line we are
            // creating a new intent
            val i = Intent(
                this@MainActivity,
                LoginActivity::class.java
            )
            // on below line we are
            // starting a new activity.
            startActivity(i)

            // on the below line we are finishing
            // our current activity.
            finish()
        }, 2000)

    }
}