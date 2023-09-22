package com.example.environmentnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.environmentnews.business.model.Favorite
import com.example.environmentnews.business.repos.MoreNewRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MoreNewRepository) : ViewModel() {
    val allFavorites: LiveData<List<Favorite>> = repository.allFavorites

    suspend fun insertFavorite(favorite: Favorite) = viewModelScope.launch{
        repository.insertFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) = viewModelScope.launch{
        repository.deleteFavorite(favorite)
    }
}