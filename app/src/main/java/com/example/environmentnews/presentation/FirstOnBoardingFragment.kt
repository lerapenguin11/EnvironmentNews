package com.example.environmentnews.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.environmentnews.MainActivity
import com.example.environmentnews.R
import com.example.environmentnews.databinding.FragmentFirstOnboardingBinding
import com.example.environmentnews.utilits.replaceFragmentMain

class FirstOnBoardingFragment : Fragment() {
    private var _binding : FragmentFirstOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstOnboardingBinding.inflate(inflater, container, false)

        binding.btNext.setOnClickListener { replaceFragmentMain(SecondOnBoardingFragment()) }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }
}