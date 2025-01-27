package com.example.recipieapp.fragments



import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipieapp.R
import com.example.recipieapp.activies.MainActivity
import com.example.recipieapp.activies.MealActivity
import com.example.recipieapp.adapter.PopularAdapter


import com.example.recipieapp.databinding.FragmentHomeBinding
import com.example.recipieapp.pojo.Recipe

import com.example.recipieapp.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var viewModel:HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularItemAdapter: PopularAdapter
    private lateinit var randomMeal :Recipe


    companion object{
        const val MEAL_ID = "com.example.recipieapp.fragments.id"
        const val MEAL_NAME = "com.example.recipieapp.fragments.title"
        const val MEAL_IMAGE = "com.example.recipieapp.fragments.image"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         viewModel=(activity as MainActivity).viewModel
        popularItemAdapter = PopularAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container
        ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePopularItemsRecyclerView()

        observePopularItemLiveData()
        onPopularItemClick()

onSearchIconClick()


    }

    private fun onSearchIconClick() {

        binding.searchView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }


    private fun onPopularItemClick() {
       popularItemAdapter.onItemClick ={meal ->
           val intent =Intent(activity,MealActivity::class.java)
           intent.putExtra(MEAL_ID,meal.id)
           intent.putExtra(MEAL_NAME,meal.title)
           intent.putExtra(MEAL_IMAGE,meal.image)
           startActivity(intent)
       }
    }

    private fun preparePopularItemsRecyclerView(){
        binding.recyclerpopular.apply {
            layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

            adapter= popularItemAdapter
        }
    }

    private fun observePopularItemLiveData() {
       viewModel.observeItemLiveData().observe(viewLifecycleOwner,
        {
            mealList ->
            popularItemAdapter.setMeals(mealsList = mealList as ArrayList<Recipe>)


        })

    }


}