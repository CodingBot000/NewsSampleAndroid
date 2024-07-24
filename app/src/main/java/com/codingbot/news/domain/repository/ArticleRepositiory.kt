package com.codingbot.news.domain.repository

import com.codingbot.news.domain.model.NewsResponse
import retrofit2.Response


interface ArticleRepositiory {
    suspend fun articles(q: String): Response<NewsResponse>
}