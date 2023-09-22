package com.example.environmentnews.business.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.environmentnews.business.model.MoreNew

@Dao
interface MoreNewDao {

    @Query("SELECT * FROM new")
    fun getAllNew(): LiveData<List<MoreNew>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNew(new: MoreNew)

    @Delete
    fun deleteNew(new: MoreNew)
}