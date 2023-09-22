package com.example.environmentnews.business.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "newsId")
    val newsId: Int,
    @ColumnInfo(name = "title")
    val title : Int,
    @ColumnInfo(name = "icon")
    val icon : Int,
    @ColumnInfo(name = "description")
    val description : Int
)


