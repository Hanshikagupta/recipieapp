package com.example.recipieapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipieapp.databinding.NutritionBinding
import com.example.recipieapp.pojo.NutrientX

class NutrientsAdapter (): RecyclerView.Adapter<NutrientsAdapter.NutritentViewHolder>()  {

    private var nutrientList= ArrayList<NutrientX>()

    fun setNutrientList(nutritionList: List<NutrientX>){
        this.nutrientList= nutritionList as ArrayList<NutrientX>
        notifyDataSetChanged()
    }

    class NutritentViewHolder (val binding: NutritionBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NutrientsAdapter.NutritentViewHolder {
        return NutritentViewHolder(NutritionBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return nutrientList.size
    }

    override fun onBindViewHolder(holder: NutrientsAdapter.NutritentViewHolder, position: Int) {
        val nutrient =nutrientList[position]

        holder.binding.namenutrition.text=nutrient.name
        holder.binding.neededamt.text=nutrient.percentOfDailyNeeds.toString()
        holder.binding.neededunit.text=nutrient.unit
        holder.binding.pesentamount.text=nutrient.amount.toString()
        holder.binding.presentunit.text=nutrient.unit

    }
}