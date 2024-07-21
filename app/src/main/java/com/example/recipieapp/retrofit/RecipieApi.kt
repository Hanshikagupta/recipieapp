package com.example.recipieapp.retrofit

import androidx.room.util.query
import com.example.recipieapp.pojo.IngradientList
import com.example.recipieapp.pojo.Recipe
import com.example.recipieapp.pojo.RecipieList
import com.example.recipieapp.pojo.EquipmentList

import com.example.recipieapp.pojo.NutritionList
import com.example.recipieapp.pojo.SearchList
import com.example.recipieapp.pojo.SummaryList
import com.example.recipieapp.pojo.TasteList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipieApi {

    @GET("random?number=10&apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getRandomRecipie():Call<RecipieList>

    @GET("{id}/tasteWidget.json?apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getTaste(@Path("id") id:Int):Call<TasteList>

    @GET("{id}/ingredientWidget.json?apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getIngradients(@Path("id") id:Int):Call<IngradientList>

    @GET("{id}/equipmentWidget.json?apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getEquipment(@Path("id") id:Int):Call<EquipmentList>

    @GET("{id}/summary?apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getSummary(@Path("id")id:Int):Call<SummaryList>
    @GET("{id}/nutritionWidget.json?apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getNutrition(@Path("id")id:Int):Call<NutritionList>

    @GET("complexSearch?apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getSerch(@Query("query")searchQuery: String):Call<SearchList>

}