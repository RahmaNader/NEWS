package com.example.news

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.adapter.AllNewsAdapter
import com.example.news.data.News
import com.example.news.network.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {

    lateinit var allNewsRecView: RecyclerView

    lateinit var allNewsAdapter: AllNewsAdapter

    private lateinit var allNewsLayout: LinearLayout

    val allNewsLayoutManager = LinearLayoutManager(this)

    var totalAllNews = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        allNewsRecView = findViewById(R.id.allNewsRecView)
        allNewsLayout = findViewById(R.id.allNewsLayout)

        getAllNews()
        showAll()

    }

    private fun showAll() {
        allNewsLayout.visibility = View.VISIBLE
    }

    private fun getAllNews() {
        val news = NewsService.newsInstance.getAllNews("all")
        news.enqueue(object : Callback<News> {

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    totalAllNews = news.totalResults
                    allNewsAdapter = AllNewsAdapter(this@MainActivity)
                    allNewsAdapter.clear()
                    allNewsAdapter.addAll(news.articles)
                    allNewsRecView.adapter = allNewsAdapter
                    allNewsRecView.layoutManager = allNewsLayoutManager

                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error", "Error in Fetching News", t)
            }

        })
    }

}