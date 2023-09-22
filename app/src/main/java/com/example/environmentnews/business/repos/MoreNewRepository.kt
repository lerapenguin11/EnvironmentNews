package com.example.environmentnews.business.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.environmentnews.business.database.FavoriteDao
import com.example.environmentnews.business.database.MoreNewDao
import com.example.environmentnews.business.model.Favorite
import com.example.environmentnews.business.model.FeaturedTipsModel
import com.example.environmentnews.business.model.MoreNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoreNewRepository(private val newDao: MoreNewDao, private val favoriteDao: FavoriteDao) {
    val allNew: LiveData<List<MoreNew>> = newDao.getAllNew()
    val allFavorites: LiveData<List<Favorite>> = favoriteDao.getAllFavorites()

    suspend fun insertNews(new: MoreNew) {
        newDao.insertNew(new)
    }

    suspend fun deleteNews(new: MoreNew) {
        newDao.deleteNew(new)
    }

    suspend fun insertFavorite(favorite: Favorite) {
        withContext(Dispatchers.IO) {
            favoriteDao.insertFavorite(favorite)
        }
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        withContext(Dispatchers.IO) {
            favoriteDao.deleteFavorite(favorite)
        }
    }

    fun isNewsFavorite(newsId: Int): LiveData<Boolean> {
        return allFavorites.map { favorites ->
            favorites.any { it.newsId == newsId }
        }
    }

    fun getNewListReading(new: MutableList<MoreNew>): LiveData<MutableList<MoreNew>> {
        val mutableData = MutableLiveData<MutableList<MoreNew>>()
        val list = mutableListOf<MoreNew>()

        for (i in new){
            val id = i.id
            val icon = i.icon
            val title = i.title
            val description = i.description

            val listLetterModel = MoreNew(
                id = id, icon = icon, title = title,
                description = description
            )
            list.add(listLetterModel)
        }

        mutableData.value = list

        return mutableData
    }
}