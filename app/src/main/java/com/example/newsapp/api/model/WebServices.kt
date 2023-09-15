package com.example.newsapp.api.model

import com.example.newsapp.api.SourcesResponse
import com.example.newsapp.api.model.newsResponse.NewsResponse
import com.example.newsapp.api.model.sourcesResponse.Source
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")   // url sources
    fun getSources(
        @Query("apiKey") key:String
    ):Call<SourcesResponse>


    @GET("v2/everything")   // url sources
    fun getNews(
        @Query("apiKey") key:String ,
        @Query("sources") sources:String
    ):Call<NewsResponse>
}