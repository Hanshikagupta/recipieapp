package com.example.recipieapp.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipieapp.databinding.IngradientBinding
import com.example.recipieapp.pojo.Recipe


class favRecipeAdapter :RecyclerView.Adapter<favRecipeAdapter.favRecipeAdapterViewHolder>(){
  inner  class favRecipeAdapterViewHolder(val binding: IngradientBinding)
      :RecyclerView.ViewHolder(binding.root)
    private val difUtil =object :DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem==newItem
        }

    }
    val differ = AsyncListDiffer(this,difUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favRecipeAdapterViewHolder {
        return  favRecipeAdapterViewHolder(
            IngradientBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: favRecipeAdapterViewHolder, position: Int) {
        val recipe= differ.currentList[position]
        Log.d("ttttt",recipe.id.toString() )
        Glide.with(holder.itemView).load(recipe.image).into(holder.binding.ingradientimage)
        holder.binding.igradintname.text=recipe.title
    }


}