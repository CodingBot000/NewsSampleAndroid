package com.codingbot.news.domain.repository

import com.codingbot.news.core.common.Const
import com.codingbot.news.domain.datasource.ArticleDataSource
import com.codingbot.news.domain.model.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class ArticleRepositoryImpl  @Inject constructor(
    private val articleDataSource: ArticleDataSource,
): ArticleRepositiory {
    override suspend fun articles(q: String): Response<NewsResponse> =
        articleDataSource.articles(q = q,
             apiKey = Const.API_KEY
            )

}