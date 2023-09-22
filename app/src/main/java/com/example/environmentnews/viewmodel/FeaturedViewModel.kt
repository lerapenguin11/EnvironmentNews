package com.example.environmentnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.environmentnews.business.model.Constants
import com.example.environmentnews.business.model.FeaturedTipsModel
import com.example.environmentnews.business.repos.FeaturedTipsRepository

class FeaturedViewModel : ViewModel() {

    private val repository = FeaturedTipsRepository()

    fun getResultFeaturedTips(): LiveData<MutableList<FeaturedTipsModel>> {
        val mutableData = MutableLiveData<MutableList<FeaturedTipsModel>>()
        repository.getFeaturedListReading(Constants.getFeaturedTips()).observeForever { list ->
            mutableData.value = list
        }

        return mutableData
    }
}