package com.example.news.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.data.Article


class AllNewsAdapter(private val context: Context): RecyclerView.Adapter<AllNewsAdapter.ArticleViewHolder>(){

    private var articles: ArrayList<Article> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.news_cards,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.title.text = article.title

        if(article.urlToImage =="" || article.urlToImage=="null") {
            article.urlToImage = "mipmap-xxxhdpi/not_available.png"
        }


        if(article.author != null){
            holder.author.text = article.author.toString()
        }else{
            holder.author.text = ""
        }

        Glide.with(context)
            .load(article.urlToImage)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.imageView_singleAllNews)
        var title: TextView = itemView.findViewById(R.id.title_singleAllNews)
        val author: TextView = itemView.findViewById(R.id.author_singleAllNews)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        articles.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(art: List<Article>) {
        articles.addAll(art)
        notifyDataSetChanged()
    }

}