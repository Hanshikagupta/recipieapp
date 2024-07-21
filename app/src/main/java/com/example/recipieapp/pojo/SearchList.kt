package com.example.recipieapp.pojo

data class SearchList(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)