package com.example.tripto.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.tripto.R
import com.example.tripto.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding object
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController= Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

    }

    }