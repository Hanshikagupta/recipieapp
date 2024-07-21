package com.example.recipieapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipieapp.dp.RecipeDataBase
import com.example.recipieapp.pojo.IngradientList
import com.example.recipieapp.pojo.IngredientX
import com.example.recipieapp.pojo.Recipe
import com.example.recipieapp.pojo.RecipieList
import com.example.recipieapp.pojo.SearchList
import com.example.recipieapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.SplittableRandom

class HomeViewModel(private val recipeDataBase: RecipeDataBase):ViewModel() {
    private var randomMealLiveData =MutableLiveData<List<Recipe>>()
    private var favoriteRecipeLiveData= recipeDataBase.recipeDao().getAllRecipe()
private val getSearchLiveData= MutableLiveData<List<com.example.recipieapp.pojo.Result>>()



    init {
        getRandomMeal()
    }
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomRecipie().enqueue(object : Callback<RecipieList> {
            override fun onResponse(call: Call<RecipieList>, response: Response<RecipieList>) {
                if (response.body()!=null)
                {

                    randomMealLiveData.value=response.body()!!.recipes
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<RecipieList>, t: Throwable) {
                Log.d("Home",t.message.toString())

            }

        })
    }
    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch {
            recipeDataBase.recipeDao().delete(recipe)
        }
    }

    fun insertRecipe(recipe: Recipe){
        viewModelScope.launch {
            recipeDataBase.recipeDao().insertRecipe(recipe)

        }
    }

fun getSearch(searchQuery: String)=RetrofitInstance.api.getSerch(searchQuery).enqueue(
    object :Callback<SearchList>{
        override fun onResponse(call: Call<SearchList>, response: Response<SearchList>) {
            val recipeList =response.body()?.results
            recipeList?.let {
                getSearchLiveData.postValue(it)
            }
        }

        override fun onFailure(call: Call<SearchList>, t: Throwable) {
            Log.d("Home",t.message.toString())
        }

    }
)
    fun observeGetSearchLiveData():LiveData<List<com.example.recipieapp.pojo.Result>>{
        return getSearchLiveData
    }
    fun observeItemLiveData():LiveData<List<Recipe>>{
        return randomMealLiveData
    }
    fun observefavoriteRecipeLiveData():LiveData<List<Recipe>>{
        return favoriteRecipeLiveData
    }


}