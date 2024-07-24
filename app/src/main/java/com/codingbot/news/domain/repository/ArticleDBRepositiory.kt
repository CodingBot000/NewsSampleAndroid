package com.codingbot.news.domain.repository

import com.codingbot.news.domain.model.ArticleStoredData


interface ArticleDBRepositiory {
    suspend fun getArticles(): List<ArticleStoredData>
    suspend fun getArticleByRow(publishedAt: String): ArticleStoredData?
    suspend fun insertArticle(article: ArticleStoredData)
    suspend fun deleteAllArticle()
    suspend fun updateIsSelected(isSelected: Boolean, publishedAt: String)
}