package com.example.environmentnews.business.model

import com.example.environmentnews.R

object Constants {

    fun getFeaturedTips() : MutableList<FeaturedTipsModel>{
        val featuredTipsList = mutableListOf<FeaturedTipsModel>()

        val featured1 = FeaturedTipsModel(0, R.drawable.ic_tip1, R.string.featured_title1, R.string.featured_temp1,
            R.string.featured_weather1, R.string.featured_weather1, R.string.featured_description1)
        featuredTipsList.add(featured1)

        val featured2 = FeaturedTipsModel(1, R.drawable.ic_tip2, R.string.featured_title2, R.string.featured_temp2,
            R.string.featured_weather2, R.string.featured_weather2, R.string.featured_description2)
        featuredTipsList.add(featured2)

        val featured3 = FeaturedTipsModel(2, R.drawable.ic_tip3, R.string.featured_title3, R.string.featured_temp3,
            R.string.featured_weather3, R.string.featured_weather3, R.string.featured_description3)
        featuredTipsList.add(featured3)

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

        val new2 = MoreNew(1, R.string.new_title2, R.drawable.ic_new2, R.string.new_desc2)
        newList.add(new2)

        val new3 = MoreNew(2, R.string.new_title3, R.drawable.ic_new3, R.string.new_desc3)
        newList.add(new3)


        return newList
    }
}