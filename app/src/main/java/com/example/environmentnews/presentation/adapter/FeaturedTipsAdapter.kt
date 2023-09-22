package com.example.environmentnews.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.environmentnews.R
import com.example.environmentnews.business.model.FeaturedTipsModel

class FeaturedTipsAdapter : RecyclerView.Adapter<FeaturedTipsAdapter.FeaturedTipsViewHolder>() {

    private val featuredTipsList = mutableListOf<FeaturedTipsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedTipsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_featured_tips, parent, false)

        return FeaturedTipsViewHolder(view)
    }

    override fun getItemCount(): Int = featuredTipsList.size

    override fun onBindViewHolder(holder: FeaturedTipsViewHolder, position: Int) {
        val featuredTips = featuredTipsList[position]

        holder.title.setText(featuredTips.title)
        holder.icon.setImageResource(featuredTips.icon)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(tips : List<FeaturedTipsModel>){
        featuredTipsList.clear()
        featuredTipsList.addAll(tips)
        notifyDataSetChanged()
    }

    class FeaturedTipsViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val icon : ImageView = view.findViewById(R.id.icon_tip)
        val title : TextView = view.findViewById(R.id.tv_title_tip)
    }
}