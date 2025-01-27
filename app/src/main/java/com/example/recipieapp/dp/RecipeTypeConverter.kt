package com.example.recipieapp.dp


import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.jar.Attributes

@TypeConverters
class RecipeTypeConverter{

    @TypeConverter
    fun fromAnyToString(attribute: Any?):String{
        if (attribute==null)
            return ""
        return attribute as String
    }
    @TypeConverter
    fun fromStringToAny( attribute: String?):Any{
        if (attribute==null)
            return ""
        return attribute
    }

}