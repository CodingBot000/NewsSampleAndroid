package com.codingbot.news.core.utils

import com.codingbot.news.domain.model.Article
import com.codingbot.news.domain.model.ArticleStoredData
import com.codingbot.news.domain.model.Source

fun articleListgDataMapper_storedDataToListData(articlesLocal: List<ArticleStoredData>): List<Article>
    = articlesLocal.map { localData ->
            Article(
                source = Source(
                    id = localData.id_source,
                    name = localData.name_source
                ),
                author = localData.author,
                title = localData.title,
                description = localData.description,
                url = localData.url,
                urlToImage = localData.urlToImage,
                publishedAt = localData.publishedAt,
                content = localData.content,
                isSelected = localData.isSelected
            )
        }


fun articleDataMapper_storedDataToListData(localData: ArticleStoredData): Article
    =  Article(
            source = Source(
                id = localData.id_source,
                name = localData.name_source
            ),
            author = localData.author,
            title = localData.title,
            description = localData.description,
            url = localData.url,
            urlToImage = localData.urlToImage,
            publishedAt = localData.publishedAt,
            content = localData.content,
            isSelected = localData.isSelected
        )



fun articleDataMapper_listDataToStoredData(article: Article): ArticleStoredData
{
    return ArticleStoredData(
        id_source = article.source.id,
        name_source = article.source.name,
        author = article.author,
        title = article.title,
        description = article.description,
        url = article.url,
        urlToImage = article.urlToImage,
        publishedAt = article.publishedAt,
        content = article.content,
        isSelected = article.isSelected
    )
}

