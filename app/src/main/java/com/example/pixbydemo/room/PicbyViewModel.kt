package com.example.pixbydemo.room

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pixbydemo.network.RetrofitUtil
import com.example.pixbydemo.room.PixbyEntity
import com.example.pixbydemo.room.PixbyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PicbyViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepository: PixbyRepository
    var success = MutableLiveData<PixbyEntity>()
    var error = MutableLiveData<Throwable>()
    var mAllUsers: LiveData<List<PixbyEntity>>


    @SuppressLint("CheckResult")
    fun getNewsList() {
        RetrofitUtil.apiService().getNewsList(
            country = "IN",
            apiKey = "54142fec704f4249ba65faf5803600fc"

        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onSuccess(it) }, {
                onFailure(
                    it
                )
            })
    }

    private fun onFailure(it: Throwable?) {
        error.postValue(it)

    }

    private fun onSuccess(it: PixbyEntity?) {
        success.postValue(it)
    }


    fun insert(pe: PixbyEntity) {
        userRepository.insert(pe)
    }

    init {
        userRepository = PixbyRepository(application)
        mAllUsers = userRepository.getAllUsers()

    }

}