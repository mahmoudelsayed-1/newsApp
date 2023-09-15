package com.example.newsapp.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(var newsList:List<News?>?=null):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
        parent,false
            )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList!![position]
        holder.itemBinding.title.text = news?.title
        // holder.itemBinding.descr.text = news?.description
        holder.itemBinding.news = news
        holder.itemBinding.invalidateAll()  // to quick loading bind item

//        Glide.with(holder.itemView).load(news?.urlToImage)
//            .placeholder(R.drawable.ic_launcher_foreground)
//            .into(holder.itemBinding.img)
    }

    override fun getItemCount(): Int = newsList?.size?:0
    fun showNews(articles: List<News?>?) {
        newsList = articles
        notifyDataSetChanged()
    }

    class ViewHolder(val itemBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemBinding.root)
}