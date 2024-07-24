package com.codingbot.news.domain.datasource

import com.codingbot.news.domain.service.ApiService
import com.codingbot.news.domain.model.NewsResponse
import retrofit2.Response
import javax.inject.Inject


class ArticleDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : ArticleDataSource {
    override suspend fun articles(q: String, apiKey: String): Response<NewsResponse> {
        return apiService.getTopHeadlines(
            q = q,
            apiKey = apiKey)
    }
}