package com.example.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.Articles

class NewsAdapter(private val newsList:ArrayList<Articles>, private val listener: INewsAdapter): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.article_title)
        val newsImage: ImageView = itemView.findViewById(R.id.article_image)
        val description: TextView =  itemView.findViewById(R.id.desc)
        val publishDate: TextView =  itemView.findViewById(R.id.publish_date)
        val cardLayout: ConstraintLayout = itemView.findViewById(R.id.news_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val viewHolder = NewsViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.news_item, parent, false))
        viewHolder.cardLayout.setOnClickListener {
            listener.onItemClicked(newsList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        if(newsItem.urlToImage=="")
            newsItem.urlToImage="drawable/ic_launcher_foreground.xml"
        holder.title.text = newsItem.title
        holder.description.text = newsItem.description
        holder.publishDate.text = newsItem.publishedAt
        Glide.with(holder.itemView.context).load(newsItem.urlToImage).centerCrop().into(holder.newsImage)
    }
}

interface INewsAdapter{
    fun onItemClicked(article: Articles)
}