package com.example.environmentnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.environmentnews.business.model.Constants
import com.example.environmentnews.business.model.FeaturedTipsModel
import com.example.environmentnews.business.model.MoreNew
import com.example.environmentnews.business.repos.MoreNewRepository

class NewViewModel(private val repository: MoreNewRepository) : ViewModel(){

    val allNews: LiveData<List<MoreNew>> = repository.allNew

    suspend fun insertNews(new: MoreNew) {
        repository.insertNews(new)
    }

    suspend fun deleteNews(new: MoreNew) {
        repository.deleteNews(new)
    }

    fun isNewsFavorite(newId: Int): LiveData<Boolean> {
        return repository.isNewsFavorite(newId)
    }

    fun getResultNew(): LiveData<MutableList<MoreNew>> {
        val mutableData = MutableLiveData<MutableList<MoreNew>>()
        repository.getNewListReading(Constants.getNew()).observeForever { list ->
            mutableData.value = list
        }

        return mutableData
    }
}