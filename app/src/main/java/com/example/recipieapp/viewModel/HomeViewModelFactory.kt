package com.example.recipieapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipieapp.dp.RecipeDataBase

class HomeViewModelFactory(
   private val recipeDataBase: RecipeDataBase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(recipeDataBase) as T
    }
}