package com.example.environmentnews.business.model

import com.example.environmentnews.R

object Constants {

    fun getFeaturedTips() : MutableList<FeaturedTipsModel>{
        val featuredTipsList = mutableListOf<FeaturedTipsModel>()

        val featured1 = FeaturedTipsModel(0, R.drawable.ic_tip1, R.string.featured_title1, R.string.featured_temp1,
            R.string.featured_weather1, R.string.featured_weather1, R.string.featured_description)

        featuredTipsList.add(featured1)

        return featuredTipsList
    }

    fun getPopularTips() : PopularTipsModel{
        val popularTips = PopularTipsModel(0, R.string.popular_title, R.drawable.ic_popular_tip,
                                            R.string.popular_desc)

        return popularTips
    }

    fun getNew() : MutableList<MoreNew>{
        val newList = mutableListOf<MoreNew>()

        val new1 = MoreNew(0, R.string.new_title1, R.drawable.ic_new1, R.string.new_desc1)
        newList.add(new1)

        return newList
    }
}