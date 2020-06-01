package com.example.pixbydemo.model

data class PixbyModel(
    val articles: List<Hit>,
    val totalResults: Int,
    val status: String
){
    data class Hit(
       val author :String,
       val title :String,
       val description :String,
       val url :String,
       val urlToImage :String
    )
}