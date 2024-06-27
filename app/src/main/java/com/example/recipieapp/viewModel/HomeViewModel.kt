package com.example.recipieapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipieapp.pojo.Recipe
import com.example.recipieapp.pojo.RecipieList
import com.example.recipieapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel:ViewModel() {
    private var randomMealLiveData =MutableLiveData<List<Recipe>>()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomRecipie().enqueue(object : Callback<RecipieList> {
            override fun onResponse(call: Call<RecipieList>, response: Response<RecipieList>) {
                if (response.body()!=null)
                {
                    Log.d("test","hjhggjy")
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
    fun observeItemLiveData():LiveData<List<Recipe>>{
        return randomMealLiveData
    }
}