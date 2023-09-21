package com.example.environmentnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.environmentnews.databinding.ActivityMainBinding
import com.example.environmentnews.utilits.APP_ACTIVITY
import com.example.environmentnews.utilits.setStatusBarGradiantMain

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setStatusBarGradiantMain(this)
        setContentView(binding.root)
    }
}