package com.example.pixbydemo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pixbydemo.model.PixbyModel

@Entity(tableName = "pixby_table")
data class PixbyEntity(
    @PrimaryKey(autoGenerate = true)
    var mid: Int? = null,
    @ColumnInfo var articles: List<PixbyModel.Hit>? = null,
    @ColumnInfo var status: String? = null)


