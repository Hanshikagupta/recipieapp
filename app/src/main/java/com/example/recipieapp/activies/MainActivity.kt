package com.example.recipieapp.activies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.recipieapp.R
import com.example.recipieapp.dp.RecipeDataBase
import com.example.recipieapp.viewModel.HomeViewModel
import com.example.recipieapp.viewModel.HomeViewModelFactory
import com.example.recipieapp.viewModel.MealViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by lazy {
        val recipeDatabase =RecipeDataBase.getInstance(this)
        val homeViewModelProviderFactory =HomeViewModelFactory(recipeDatabase)
        ViewModelProvider(this,homeViewModelProviderFactory)[HomeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bottomNavigation =findViewById<BottomNavigationView>(R.id.bottomnav)
        val navController =Navigation.findNavController(this, R.id.fragment)

        NavigationUI.setupWithNavController(bottomNavigation,navController)

    }
}