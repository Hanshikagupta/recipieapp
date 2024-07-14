package com.example.recipieapp.activies

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager


import com.bumptech.glide.Glide
import com.example.recipieapp.adapter.IngradientAdapter
import com.example.recipieapp.adapter.EquipmentAdapter

import com.example.recipieapp.databinding.ActivityMealBinding
import com.example.recipieapp.dp.RecipeDataBase
import com.example.recipieapp.fragments.HomeFragment
import com.example.recipieapp.pojo.Recipe
import com.example.recipieapp.viewModel.MealViewModel
import com.example.recipieapp.viewModel.RecipeViewModelFactory

class MealActivity : AppCompatActivity()
{
    private var mealid:Int? =null
    private  var mealname:String? =null
    private  var mealimage:String? =null
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealmvvm: MealViewModel
    private lateinit var ingradientAdapter: IngradientAdapter
    private lateinit var equipmentAdapter: EquipmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ingradientAdapter=IngradientAdapter()
        equipmentAdapter= EquipmentAdapter()
        val recipeDataBase =RecipeDataBase.getInstance(this)
        val viewModelFactory =RecipeViewModelFactory(recipeDataBase)
        mealmvvm = ViewModelProvider(this,viewModelFactory)[MealViewModel::class.java]


        onFavoriteClick()
        getMealInformationFromIntent()

        setInfoInViews()
        mealmvvm.getMealDetail(mealid)
        observerMealDetailsLiveData()

        prepareIngradientRecyclerView()
        mealmvvm.getIngradient(mealid)
        observeIngradientLivedata()

        prepareEquipmentRecyclerView()
        mealmvvm.getEquipment(mealid)
        observeEquipmentLivedata()

mealmvvm.getSummary(mealid)
        observeSummaryLiveData()

        mealmvvm.getNutrition(mealid)
        observeNutritionLiveData()


    }

    private fun onFavoriteClick() {
        binding.fav.setOnClickListener{
            recipeToSave?.let {
                mealmvvm.insertRecipe(it)
                Toast.makeText(this,"Meal Saved",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var recipeToSave:Recipe?=null

    private fun observeNutritionLiveData() {
        mealmvvm.observeNutritionLivedata().observe(this, Observer { nutrition ->
            binding.nutrition.text = nutrition.toString()
        })
    }

    private fun observeSummaryLiveData() {
        mealmvvm.observeSummaryLiveData().observe(this, Observer { summary ->
            binding.summary.text = summary
        })
    }


    private fun prepareEquipmentRecyclerView() {
        binding.recyclerequipment.apply {
            layoutManager =LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter= equipmentAdapter
        }
    }
 
    private fun observeEquipmentLivedata() {
        mealmvvm.observeEquipmentLivedata().observe(this, Observer { equipments ->
           equipmentAdapter.setEquipmentList(equipments)
        })
    }

    private fun prepareIngradientRecyclerView() {
        binding.recycleringrdiant.apply {
            layoutManager =LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter= ingradientAdapter
        }

    }

    private fun observeIngradientLivedata() {
        mealmvvm.observeIngradientLivedata().observe(this, Observer { ingradients->

                ingradientAdapter.setIngradientList(ingradients)

        })
    }


    private fun observerMealDetailsLiveData() {
        mealmvvm.observerMealDetailsLiveData().observe(this,object :Observer<Recipe> {

            // Update UI with meal details


            override fun onChanged(value: Recipe) {

                val meal=value
                recipeToSave=meal
                binding.readyin.text = "Time: ${meal.readyInMinutes}"
                binding.serving.text = "Serving: ${meal.servings}"
                binding.price.text = "Price: ${meal.pricePerServing}"
                binding.instruction.text = "${meal.instructions}"
            }

        })
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

        if (mealid ==-1 || mealname == null || mealimage == null) {
            // Handle the case where some extras are missing
            // For example, show an error message or finish the activity
            finish()
            }

    }
}