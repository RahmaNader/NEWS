package com.example.news.newsnetwork


import android.util.Log
import com.example.news.model.Articles
import com.example.news.model.News
import com.example.news.retrofitservice.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsNetwork(val listener: ISuccessListener) {
    fun getNews( category : String){
        val news: Call<News> = NewsService.retrofitInstance.getHeadLines(category)
        Log.i("url", news.request().toString())
        news.enqueue(object: Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.i("Error","${t.message}")
            }
            override fun onResponse(call: Call<News>, response: Response<News>) {
                Log.i("news Success", response.body().toString())
                val currNews: News? = response.body()
                if (currNews != null){
                    listener.onNewsSuccess(currNews.articles)
                }
            }

        })

    }
}

interface ISuccessListener{
    fun onNewsSuccess(news: ArrayList<Articles>)
}


