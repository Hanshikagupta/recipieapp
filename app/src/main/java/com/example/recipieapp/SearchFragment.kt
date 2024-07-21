package com.example.recipieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipieapp.activies.MainActivity
import com.example.recipieapp.adapter.SearchRecipeAdapter
import com.example.recipieapp.adapter.favRecipeAdapter
import com.example.recipieapp.databinding.FragmentSearchBinding
import com.example.recipieapp.viewModel.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {
private lateinit var binding: FragmentSearchBinding
private lateinit var viewModel: HomeViewModel
private lateinit var seachRecyclerViewAdapter: SearchRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel=(activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
binding=FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()

        binding.imgSearch.setOnClickListener {
            searchMeals()
        }
        observeSearchMealLiveData()

        var searchJob:Job?=null
        binding.editSearch.addTextChangedListener {searchQuery->
            searchJob?.cancel()
            searchJob= lifecycleScope.launch {
                delay(500)
                viewModel.getSearch(searchQuery.toString())
            }
        }
    }

    private fun observeSearchMealLiveData() {
        viewModel.observeGetSearchLiveData().observe(viewLifecycleOwner, Observer { recipeList ->
            seachRecyclerViewAdapter.differ.submitList(recipeList)
        })
    }

    private fun searchMeals() {
        val searchQuery =binding.editSearch.text.toString()
        if (searchQuery.isNotEmpty()){
            viewModel.getSearch(searchQuery)
        }
    }

    private fun prepareRecyclerView() {
        seachRecyclerViewAdapter= SearchRecipeAdapter()
        binding.recyclerSearch.apply {
            layoutManager=LinearLayoutManager(context)
            adapter= seachRecyclerViewAdapter
        }
    }
}
