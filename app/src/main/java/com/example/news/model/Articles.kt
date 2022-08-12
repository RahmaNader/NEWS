package com.example.news.model
data class Articles (
    val title: String,
    val author: String,
    val description: String,
    val url: String,
    var urlToImage:String,
    val publishedAt:String
)