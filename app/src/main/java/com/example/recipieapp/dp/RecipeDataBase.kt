package com.example.recipieapp.dp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.recipieapp.pojo.Recipe

@Database(entities = [Recipe::class], version = 1 , exportSchema = false)
@TypeConverters (RecipeTypeConverter::class)
abstract class RecipeDataBase:RoomDatabase() {
    abstract fun recipeDao():RecipieDao

    companion object{
        @Volatile
        var INSTANCE:RecipeDataBase?=null
        @Synchronized
        fun getInstance(context: Context):RecipeDataBase{
            if (INSTANCE==null)
            {
                INSTANCE=Room.databaseBuilder(
                    context,
                    RecipeDataBase::class.java,
                    "recipe.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as RecipeDataBase
        }
    }
}