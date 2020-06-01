package com.example.pixbydemo.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pixbydemo.R
import com.example.pixbydemo.model.PixbyModel
import com.example.pixbydemo.room.PicbyViewModel
import com.example.pixbydemo.room.PixbyEntity
import com.gohelpmate.adapter.PixbyAdapters
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var list = ArrayList<PixbyModel.Hit>()

    lateinit var picbyViewModel: PicbyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picbyViewModel = ViewModelProviders.of(this).get(PicbyViewModel::class.java)
        init()
    }


    private fun init() {
        picbyViewModel.getNewsList()
        picbyViewModel.success.observe(this, Observer {
            updateUi(true)
            Log.e("data", it.toString())
            picbyViewModel.insert(it)

        })
        picbyViewModel.error.observe(this, Observer {
            updateUi(true)

        })

        picbyViewModel.mAllUsers.observe(this, Observer {
            updateUi(true)
            setAdapyer(it[0].articles!!)
        })

    }

    fun setAdapyer(it: List<PixbyModel.Hit>) {
        list = it as ArrayList
        if (list.size > 0) {
            Log.d("size","list size :  "+list.size)
            rv_images.adapter = PixbyAdapters(this, list)
            rv_images.setLayoutManager(GridLayoutManager(this, 1))
        }

    }


    fun updateUi(isEnable: Boolean) {
        progress_bar.visibility = if (isEnable) View.GONE else View.VISIBLE
    }


}
