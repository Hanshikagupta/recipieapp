package com.example.recipieapp.pojo

data class Step(
    val equipment: List<Any>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)