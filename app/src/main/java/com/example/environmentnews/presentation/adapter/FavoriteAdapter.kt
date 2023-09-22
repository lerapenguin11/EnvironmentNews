package com.example.environmentnews.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.environmentnews.R
import com.example.environmentnews.business.model.Favorite

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    val favoriteList = mutableListOf<Favorite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_save, parent, false)

        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = favoriteList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = favoriteList[position]

        holder.description.setText(favorite.description)
        holder.icon.setImageResource(favorite.icon)
        holder.title.setText(favorite.title)
    }

    fun setItem(fav : List<Favorite>){
        favoriteList.clear()
        favoriteList.addAll(fav)
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val icon : ImageView = view.findViewById(R.id.icon_new_save)
        val title : TextView = view.findViewById(R.id.tv_save_title)
        val description : TextView = view.findViewById(R.id.tv_save_desc)
    }
}