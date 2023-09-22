package com.example.environmentnews.business.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.environmentnews.business.model.FeaturedTipsModel

class FeaturedTipsRepository {

    fun getFeaturedListReading(tips: MutableList<FeaturedTipsModel>): LiveData<MutableList<FeaturedTipsModel>> {
        val mutableData = MutableLiveData<MutableList<FeaturedTipsModel>>()
        val list = mutableListOf<FeaturedTipsModel>()

        for (i in tips){
            val id = i.id
            val icon = i.icon
            val title = i.title
            val temp = i.temp
            val weather = i.weather
            val water = i.water
            val description = i.description

            val listLetterModel = FeaturedTipsModel(
                id = id, icon = icon, title = title, temp = temp,
                weather = weather, water = water, description = description
            )
            list.add(listLetterModel)
        }

        mutableData.value = list

        return mutableData
    }
}