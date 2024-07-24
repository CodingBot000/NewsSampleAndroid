package com.codingbot.news.domain.service


import com.codingbot.news.domain.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}