package com.example.environmentnews.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.environmentnews.MainActivity
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

        val window: Window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(requireActivity().resources.getColor(com.example.environmentnews.R.color.background))

        binding.btNext.setOnClickListener { replaceFragmentMain(HomeFragment()) }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }
}