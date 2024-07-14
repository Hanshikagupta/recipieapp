package com.example.recipieapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val api:RecipieApi by lazy {
        Retrofit.Builder().baseUrl("https://api.spoonacular.com/recipes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipieApi::class.java )

}

}