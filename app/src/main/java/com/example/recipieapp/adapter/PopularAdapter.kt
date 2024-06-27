package com.example.recipieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipieapp.databinding.ItemsBinding
import com.example.recipieapp.pojo.Recipe
import com.example.recipieapp.pojo.RecipieList

class PopularAdapter():RecyclerView.Adapter<PopularAdapter.PopularMealViewHolder>() {
    lateinit var onItemClick :((Recipe) -> Unit)
    private var mealsList =ArrayList<Recipe>()
    fun setMeals(mealsList: ArrayList<Recipe>){
        this.mealsList=mealsList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(ItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
      return  mealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].image)
            .into(holder.binding.imagepopular)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealsList[position])
        }
    }

    class PopularMealViewHolder( val binding: ItemsBinding):RecyclerView.ViewHolder(binding.root)
}