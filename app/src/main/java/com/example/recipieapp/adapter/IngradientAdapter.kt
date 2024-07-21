package com.example.recipieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipieapp.R
import com.example.recipieapp.databinding.IngradientBinding
import com.example.recipieapp.pojo.IngredientX

class IngradientAdapter():RecyclerView.Adapter<IngradientAdapter.IngradientViewHolder>() {

private var ingradientList=ArrayList<IngredientX>()

    fun setIngradientList(ingradientList: List<IngredientX>){
        this.ingradientList=ingradientList as ArrayList<IngredientX>
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngradientAdapter.IngradientViewHolder {
        return IngradientViewHolder(
            IngradientBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: IngradientAdapter.IngradientViewHolder, position: Int) {


        val ingredient = ingradientList[position]

        // Build the full URL for the image
        val baseUrl = "https://img.spoonacular.com/ingredients_100x100/"
        val imageUrl = baseUrl + ingredient.image

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.ingradientimage)

        holder.binding.igradintname.text = ingredient.name
    }

    override fun getItemCount(): Int {
       return ingradientList.size
    }

    inner class IngradientViewHolder( val binding: IngradientBinding):RecyclerView.ViewHolder(binding.root)


}