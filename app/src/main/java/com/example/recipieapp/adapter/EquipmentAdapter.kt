package com.example.recipieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipieapp.databinding.EquipmentBinding
import com.example.recipieapp.pojo.Equipment

class EquipmentAdapter():RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>() {

    private var equipmentList= ArrayList<Equipment>()

    fun setEquipmentList(equipmentList:List<Equipment>){
        this.equipmentList= equipmentList as ArrayList<Equipment>
        notifyDataSetChanged()
    }
    inner class EquipmentViewHolder(val binding: EquipmentBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EquipmentAdapter.EquipmentViewHolder {
          return EquipmentViewHolder(
              EquipmentBinding.inflate(
                  LayoutInflater.from(parent.context)
              )
          )
    }

    override fun onBindViewHolder(holder: EquipmentAdapter.EquipmentViewHolder, position: Int) {
         val equipment =equipmentList[position]

        val baseUrl = "https://img.spoonacular.com/equipment_100x100/"
        val imageUrl = baseUrl + equipment.image

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.binding.requirementimage)

        holder.binding.requirementname .text = equipment.name
    }

    override fun getItemCount(): Int {
        return equipmentList.size
    }

}