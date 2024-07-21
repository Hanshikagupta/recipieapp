package com.example.recipieapp.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipieapp.databinding.IngradientBinding
import com.example.recipieapp.databinding.SearchItemBinding
import com.example.recipieapp.pojo.Recipe
import com.example.recipieapp.pojo.Result


class SearchRecipeAdapter :RecyclerView.Adapter<SearchRecipeAdapter.SearchRecipeAdapterViewHolder>(){
  inner  class SearchRecipeAdapterViewHolder(val binding: SearchItemBinding)
      :RecyclerView.ViewHolder(binding.root)
    private val difUtil =object :DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem==newItem
        }

    }
    val differ = AsyncListDiffer(this,difUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecipeAdapterViewHolder {
        return  SearchRecipeAdapterViewHolder(
            SearchItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SearchRecipeAdapterViewHolder, position: Int) {
        val recipe= differ.currentList[position]
        Log.d("ttttt",recipe.id.toString() )
        Glide.with(holder.itemView).load(recipe.image).into(holder.binding.ingradientimage)
        holder.binding.igradintname.text=recipe.title
    }


}