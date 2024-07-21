package com.example.recipieapp.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipieapp.pojo.Recipe
@Dao
interface RecipieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)
    @Delete
    suspend fun delete(recipe: Recipe)
    @Query("SELECT * FROM recipeInformation ")
    fun getAllRecipe():LiveData<List<Recipe>>

}