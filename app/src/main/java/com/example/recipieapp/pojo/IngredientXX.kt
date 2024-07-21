package com.example.recipieapp.pojo

data class IngredientXX(
    val amount: Double,
    val id: Int,
    val name: String,
    val nutrients: List<NutrientX>,
    val unit: String
)