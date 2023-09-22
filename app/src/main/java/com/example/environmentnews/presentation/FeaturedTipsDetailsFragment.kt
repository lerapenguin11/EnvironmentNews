package com.example.environmentnews.presentation

import android.R
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.environmentnews.MainActivity
import com.example.environmentnews.databinding.FragmentFeaturedTipsDetailsBinding
import com.example.environmentnews.utilits.replaceFragmentMain


class FeaturedTipsDetailsFragment : Fragment() {
    private var _binding : FragmentFeaturedTipsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeaturedTipsDetailsBinding.inflate(inflater, container, false)

        val window: Window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(requireActivity().resources.getColor(com.example.environmentnews.R.color.color3))

        binding.icArrow.setOnClickListener { replaceFragmentMain(HomeFragment()) }

        val displayTitle = arguments?.getInt("title")
        binding.tvDetailsFeaturedTitle.setText(displayTitle!!)

        val displayDescription = arguments?.getInt("description")
        binding.tvDesc.setText(displayDescription!!)

        val displayIcon = arguments?.getInt("icon")
        binding.icIconFeatiredDetail.setImageResource(displayIcon!!)

        val displayTemp = arguments?.getInt("temp")
        binding.tvTempDetails.setText(displayTemp!!)

        val displayWeather = arguments?.getInt("weather")
        binding.tvWeatherDetails.setText(displayWeather!!)

        val displayWater = arguments?.getInt("water")
        binding.tvWaterDetails.setText(displayWater!!)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }
}