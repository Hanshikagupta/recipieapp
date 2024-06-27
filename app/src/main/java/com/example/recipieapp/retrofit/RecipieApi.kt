package com.example.recipieapp.retrofit

import com.example.recipieapp.pojo.RecipieList
import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipieApi {

    @GET("random?number=10&apiKey=1ceba32109ce453dbf38b85ff4b91c6f")
    fun getRandomRecipie():Call<RecipieList>






}