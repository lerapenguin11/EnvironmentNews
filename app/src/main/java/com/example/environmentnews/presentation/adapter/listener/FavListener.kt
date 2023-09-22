package com.example.environmentnews.presentation.adapter.listener

import com.example.environmentnews.business.model.Favorite

interface FavListener {

    fun getFavorite(fav : Favorite)

    fun getFavoriteDelete(fav : Favorite)
}