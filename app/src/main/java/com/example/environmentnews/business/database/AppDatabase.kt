package com.example.environmentnews.business.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.environmentnews.business.model.Favorite
import com.example.environmentnews.business.model.MoreNew

@Database(entities = [MoreNew::class, Favorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newDao(): MoreNewDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "new_database_1"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}