package com.example.moviesdemo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pixbydemo.room.PixbyDao
import com.example.pixbydemo.room.PixbyEntity

@Database(entities = [PixbyEntity::class], version = 3,exportSchema = false)
@TypeConverters(ListConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun pixbyDao(): PixbyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "pixby-database"

                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

    fun destroyInstance() {
        INSTANCE = null
    }
}
