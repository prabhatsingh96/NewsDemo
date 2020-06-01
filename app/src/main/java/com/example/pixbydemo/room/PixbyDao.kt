package com.example.pixbydemo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PixbyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: PixbyEntity)

    @Query("SELECT * FROM pixby_table")
    fun getAll(): LiveData<List<PixbyEntity>>
}