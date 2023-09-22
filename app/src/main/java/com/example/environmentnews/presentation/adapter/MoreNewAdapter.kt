package com.example.environmentnews.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.environmentnews.R
import com.example.environmentnews.business.model.MoreNew

class MoreNewAdapter : RecyclerView.Adapter<MoreNewAdapter.MoreNewViewHolder>() {

    private val newList = mutableListOf<MoreNew>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreNewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_more_new, parent, false)

        return MoreNewViewHolder(view)
    }

    override fun getItemCount(): Int = newList.size

    override fun onBindViewHolder(holder: MoreNewViewHolder, position: Int) {
        val new = newList[position]

        holder.icon.setImageResource(new.icon)
        holder.title.setText(new.title)

        if (position == newList.size-1){
            holder.line.visibility = View.GONE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(new : List<MoreNew>){
        newList.clear()
        newList.addAll(new)
        notifyDataSetChanged()
    }

    class MoreNewViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val icon : ImageView = view.findViewById(R.id.icon_more_new)
        val title : TextView = view.findViewById(R.id.tv_more_new)
        val line : View = view.findViewById(R.id.line)
    }
}