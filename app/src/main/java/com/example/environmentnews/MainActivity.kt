package com.example.environmentnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.environmentnews.databinding.ActivityMainBinding
import com.example.environmentnews.presentation.FirstOnBoardingFragment
import com.example.environmentnews.presentation.HomeFragment
import com.example.environmentnews.presentation.SaveFragment
import com.example.environmentnews.utilits.APP_ACTIVITY
import com.example.environmentnews.utilits.replaceFragmentMain
import com.example.environmentnews.utilits.setStatusBarGradiantMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setStatusBarGradiantMain(this)
        setContentView(binding.root)
        replaceFragmentMain(FirstOnBoardingFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragmentMain(HomeFragment())
                R.id.save -> replaceFragmentMain(SaveFragment())
            }
            true
        }
    }
}