package com.codingbot.news.domain.datasource

import com.codingbot.news.domain.model.NewsResponse
import retrofit2.Response

interface ArticleDataSource {
    suspend fun articles(q: String, apiKey: String): Response<NewsResponse>
}