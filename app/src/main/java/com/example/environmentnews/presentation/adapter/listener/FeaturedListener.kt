package com.example.environmentnews.presentation.adapter.listener

import com.example.environmentnews.business.model.FeaturedTipsModel

interface FeaturedListener {

    fun getFeaturedTips(featured : FeaturedTipsModel)
}