package com.example.recipieapp.activies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.recipieapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bottomNavigation =findViewById<BottomNavigationView>(R.id.bottomnav)
        val navController =Navigation.findNavController(this, R.id.fragment)

        NavigationUI.setupWithNavController(bottomNavigation,navController)

    }
}