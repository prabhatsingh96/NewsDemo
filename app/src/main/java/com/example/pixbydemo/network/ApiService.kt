package com.example.pixbydemo.network

import com.example.pixbydemo.room.PixbyEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

//?key=16601883-13d1811d80900a2727edaa2f7&q=yellow+flowers&image_type=photo"

    @GET("https://newsapi.org/v2/top-headlines")
    fun getNewsList(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Observable<PixbyEntity>
}