package com.example.moviesdemo.room

import androidx.room.TypeConverter

import com.example.pixbydemo.model.PixbyModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListConverters {

    @TypeConverter
    fun toTorrent(json: String): List<PixbyModel.Hit> {
        val type = object : TypeToken<List<PixbyModel.Hit>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(torrent: List<PixbyModel.Hit>): String {
        val type = object : TypeToken<List<PixbyModel.Hit>>() {}.type
        return Gson().toJson(torrent, type)
    }

}