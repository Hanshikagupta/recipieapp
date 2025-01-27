package com.example.recipieapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import com.example.recipieapp.R
import com.example.recipieapp.activies.MainActivity
import com.example.recipieapp.adapter.favRecipeAdapter
import com.example.recipieapp.databinding.FragmentFavBinding
import com.example.recipieapp.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar


class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favoriteAdapter: favRecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel=(activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFavBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        observeFavorites()

        val itemTouchHelper =object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or  ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            )=true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position =viewHolder.adapterPosition
                viewModel.deleteRecipe(favoriteAdapter.differ.currentList[position])
                Snackbar.make(requireView(),"MealDeleted",Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {
                        viewModel.insertRecipe(favoriteAdapter.differ.currentList[position])
                    }
                ).show()

            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rvFav)
    }

    private fun prepareRecyclerView() {
        favoriteAdapter= favRecipeAdapter()
        binding.rvFav.apply {
            layoutManager =GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=favoriteAdapter
        }
    }

    private fun observeFavorites() {
        viewModel.observefavoriteRecipeLiveData().observe(requireActivity(), Observer { recipe ->
            favoriteAdapter.differ.submitList(recipe)
        })
    }


}