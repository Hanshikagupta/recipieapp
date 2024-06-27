package com.example.recipieapp.activies

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.bumptech.glide.Glide
import com.example.recipieapp.R

import com.example.recipieapp.databinding.ActivityMealBinding
import com.example.recipieapp.fragments.HomeFragment

class MealActivity : AppCompatActivity() {
    private var mealid:Int? =null
    private  var mealname:String? =null
    private  var mealimage:String? =null
    private lateinit var binding: ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMealInformationFromIntent()

        setInfoInViews()

    }

    private fun setInfoInViews() {
        Glide.with(applicationContext)
            .load(mealimage)
            .into(binding.imagemeal)

        binding.collapsingToolBar.title=mealname

    }

    private fun getMealInformationFromIntent() {
        val intent=intent
        mealid=intent.getIntExtra(HomeFragment.MEAL_ID,-1)
        mealname=intent.getStringExtra(HomeFragment.MEAL_NAME)
        mealimage=intent.getStringExtra(HomeFragment.MEAL_IMAGE)

        if (mealid == null || mealname == null || mealimage == null) {
            // Handle the case where some extras are missing
            // For example, show an error message or finish the activity
            finish()
            }

    }
}