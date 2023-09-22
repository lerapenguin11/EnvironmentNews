package com.example.environmentnews.business.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new")
data class MoreNew(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "title")
    val title : Int,
    @ColumnInfo(name = "icon")
    val icon : Int,
    @ColumnInfo(name = "description")
    val description : Int
)
