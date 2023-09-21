package com.example.environmentnews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.environmentnews.databinding.FragmentSecondOnBoardingBinding
import com.example.environmentnews.utilits.replaceFragmentMain

class SecondOnBoardingFragment : Fragment() {
    private var _binding : FragmentSecondOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondOnBoardingBinding.inflate(inflater, container, false)

        binding.btNext.setOnClickListener { replaceFragmentMain(HomeFragment()) }

        return binding.root
    }
}