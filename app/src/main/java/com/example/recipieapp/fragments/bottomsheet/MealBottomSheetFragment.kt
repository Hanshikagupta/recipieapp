package com.example.recipieapp.fragments.bottomsheet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipieapp.R
import com.example.recipieapp.activies.MainActivity
import com.example.recipieapp.databinding.FragmentMealBottomSheetBinding
import com.example.recipieapp.viewModel.HomeViewModel

private const val recipeId = "param1"

class MealBottomSheetFragment : Fragment() {


    private lateinit var binding: FragmentMealBottomSheetBinding
    private lateinit var viewModel:HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMealBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            MealBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(recipeId, param1)

                }
            }
    }
}