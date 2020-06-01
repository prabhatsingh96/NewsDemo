package com.example.pixbydemo.room

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.moviesdemo.room.AppDataBase

class PixbyRepository(application: Application) {
    lateinit var dao: PixbyDao
    var mAllusers: LiveData<List<PixbyEntity>>?=null

    init {
        val dataBase: AppDataBase? = AppDataBase.getInstance(application)
        if (dataBase != null) {
            dao = dataBase.pixbyDao()
            mAllusers = dao.getAll()
        }


    }

    fun getAllUsers():LiveData<List<PixbyEntity>>{
        return  dao.getAll()

    }

    fun insert(pd: PixbyEntity) {
        InsertAsyncTask(dao).execute(pd)
    }

    private class InsertAsyncTask internal constructor(dao: PixbyDao) :
        AsyncTask<PixbyEntity?, Unit, Unit?>() {

        private val mAsycnctask: PixbyDao
        override fun doInBackground(vararg p0: PixbyEntity?): Unit? {
            p0[0]?.let { mAsycnctask.insert(it) }
            return null
        }

        init {
            mAsycnctask = dao
        }


    }

}