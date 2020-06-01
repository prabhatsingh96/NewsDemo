package com.gohelpmate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pixbydemo.room.PixbyEntity
import com.example.pixbydemo.R
import com.example.pixbydemo.model.PixbyModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_row_nrews.view.*

class PixbyAdapters(private val context: Context, val list: ArrayList<PixbyModel.Hit>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<PixbyAdapters.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.single_row_nrews,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(position)

    }

    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun bindView(position: Int) {
            if (list.size > 0){
                Picasso.get().load(list[position].urlToImage).into(itemView.iv_image)
                itemView.tvTilte.text = list[position].title
            }
        }
    }
}



