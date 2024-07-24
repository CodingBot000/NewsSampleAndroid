package com.codingbot.news.domain.repository

import com.codingbot.news.domain.ArticleDAO
import com.codingbot.news.domain.model.ArticleStoredData
import javax.inject.Inject

class ArticleDBRepositoryImpl  @Inject constructor(
    private val articleDAO: ArticleDAO,
): ArticleDBRepositiory {
    override suspend fun getArticles(): List<ArticleStoredData> =
        articleDAO.getArticles()

    override suspend fun getArticleByRow(publishedAt: String): ArticleStoredData? =
        articleDAO.getArticleByRow(publishedAt)

    override suspend fun insertArticle(article: ArticleStoredData) =
        articleDAO.insertArticle(article)


    override suspend fun deleteAllArticle() = articleDAO.deleteAllArticle()

    override suspend fun updateIsSelected(isSelected: Boolean, publishedAt: String)
    = articleDAO.updateIsSelected(isSelected, publishedAt)
}