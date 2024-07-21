package com.example.recipieapp.viewModel

import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipieapp.dp.RecipeDataBase
import com.example.recipieapp.pojo.Equipment
import com.example.recipieapp.pojo.EquipmentList
import com.example.recipieapp.pojo.IngradientList
import com.example.recipieapp.pojo.IngredientX
import com.example.recipieapp.pojo.NutrientX
import com.example.recipieapp.pojo.NutritionList
import com.example.recipieapp.pojo.Recipe
import com.example.recipieapp.pojo.RecipieList
import com.example.recipieapp.pojo.SummaryList
import com.example.recipieapp.pojo.TasteList
import com.example.recipieapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(
    private val recipeDataBase: RecipeDataBase
):ViewModel() {
    private var mealDetailLiveData =MutableLiveData<Recipe>()
    private var ingradientLiveData= MutableLiveData<List<IngredientX>>()
    private var equipmentLiveData =MutableLiveData<List<Equipment>>()
    private var summaryLiveData =MutableLiveData<String>()
    private var nutritionLiveData =MutableLiveData<List<NutrientX>>()
    private var TasteLiveData =MutableLiveData<Double>()

    fun getTaste(id: Int?){
        if (id != null) {
            RetrofitInstance.api.getTaste(id).enqueue(object :Callback<TasteList>{
                override fun onResponse(call: Call<TasteList>, response: Response<TasteList>) {
                    if (response.isSuccessful){
                        response.body()?.let { tasteList ->
                            Log.e("MealViewModel", tasteList.sweetness.toString())
                            TasteLiveData.postValue(tasteList.sweetness)
                           TasteLiveData.postValue(tasteList.bitterness)
                           TasteLiveData.postValue(tasteList.fattiness)
                           TasteLiveData.postValue(tasteList.saltiness)
                        }
                    }
                }

                override fun onFailure(call: Call<TasteList>, t: Throwable) {
                    Log.e("MealViewModel", t.message.toString())                 }

            })
        }
    }

    fun getNutrition(id: Int?){
        if (id != null) {
            RetrofitInstance.api.getNutrition(id).enqueue(object :Callback<NutritionList>{
                override fun onResponse(call: Call<NutritionList>, response: Response<NutritionList>) {
                    if (response.isSuccessful){
                        response.body()?.let { nutritionList ->
                            nutritionLiveData.postValue(nutritionList.nutrients)
                        }
                    }
                }

                override fun onFailure(call: Call<NutritionList>, t: Throwable) {
                    Log.e("MealViewModel", t.message.toString())                }

            })
        }
    }
    fun String.toSpanned(): Spanned {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(this)
        }
    }

    fun getSummary(id: Int?) {
        id?.let {
            RetrofitInstance.api.getSummary(it).enqueue(object : Callback<SummaryList> {
                override fun onResponse(call: Call<SummaryList>, response: Response<SummaryList>) {
                    if (response.isSuccessful) {
                        response.body()?.let { summaryList ->

                            val spannedSummary = summaryList.summary.toSpanned()
                            summaryLiveData.postValue(spannedSummary.toString())
                        }
                    } else {
                        Log.d("MealViewModel", "Response not successful")
                    }
                }

                override fun onFailure(call: Call<SummaryList>, t: Throwable) {
                    Log.e("MealViewModel", t.message.toString())
                }
            })
        }
    }

    fun getEquipment(id: Int?){
        if (id != null) {
            RetrofitInstance.api.getEquipment(id).enqueue(object :Callback<EquipmentList>{
                override fun onResponse(
                    call: Call<EquipmentList>,
                    response: Response<EquipmentList>
                ) {
                    response.body()?.let {
                        EquipmentList->
                        equipmentLiveData.postValue(EquipmentList.equipment)
                    }
                }

                override fun onFailure(call: Call<EquipmentList>, t: Throwable) {
                    Log.e("MealViewmodel",t.message.toString())
                }

            })


        }
    }

    fun getMealDetail(id: Int?) {
        if (id != null) {
            RetrofitInstance.api.getRandomRecipie().enqueue(object : Callback<RecipieList> { // Update Callback type to List<Recipe>
                override fun onResponse(call: Call<RecipieList>, response: Response<RecipieList>) {
                    response.body()?.let {
                            RecipieList->
                        Log.d("Api Response",RecipieList.recipes[0].id.toString())
                        mealDetailLiveData.postValue(RecipieList.recipes[0])
                    }
                }
                override fun onFailure(call: Call<RecipieList>, t: Throwable) {
                    Log.d("API Response", t.message.toString())
                }

            })
        }
    }
    fun getIngradient(id:Int?){
        if (id != null) {
            RetrofitInstance.api.getIngradients(id).enqueue(object :Callback<IngradientList>{
                override fun onResponse(
                    call: Call<IngradientList>,
                    response: Response<IngradientList>
                ) {
                    response.body()?.let { IngradientList->


                        ingradientLiveData.postValue(IngradientList.ingredients)
                    }
                }

                override fun onFailure(call: Call<IngradientList>, t: Throwable) {
                    Log.e("MealViewmodel",t.message.toString())
                }

            })
        }
    }
    fun insertRecipe(recipe: Recipe){
        viewModelScope.launch {
            recipeDataBase.recipeDao().insertRecipe(recipe)

        }
    }


    fun observerMealDetailsLiveData(): LiveData<Recipe> {
        return mealDetailLiveData
    }
    fun observeIngradientLivedata():LiveData<List<IngredientX>>{
        return ingradientLiveData
    }
    fun observeEquipmentLivedata():LiveData<List<Equipment>>{
        return equipmentLiveData
    }
    fun observeSummaryLiveData(): LiveData<String> = summaryLiveData
    fun observeTasteLiveData(): LiveData<Double> = TasteLiveData

    fun observeNutritionLivedata():LiveData<List<NutrientX>>{
        return nutritionLiveData
    }

}