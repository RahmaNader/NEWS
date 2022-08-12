package com.example.news.retrofitservice

import com.example.news.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//7a0070dd915247bab7d4bf6f34264557
//a0e8c1c0542944b7a4f721ec7dbe6964
//https://newsapi.org/v2/top-headlines?country=us&category=business?apiKey=
const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "a0e8c1c0542944b7a4f721ec7dbe6964"

interface RetrofitService{
    @GET("v2/top-headlines?country=us&apiKey=a0e8c1c0542944b7a4f721ec7dbe6964")
    fun getHeadLines(@Query("category") category: String): Call<News>
}

object NewsService{

    val retrofitInstance: RetrofitService
    init {
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        retrofitInstance = retrofit.create(RetrofitService::class.java)
    }
}