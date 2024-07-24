package com.codingbot.news.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingbot.news.core.common.Const
import com.codingbot.news.domain.model.ArticleStoredData

@Dao
interface ArticleDAO {
    @Query("SELECT * FROM ${Const.TABLENAME_ARTICLE}")
    fun getArticles(): List<ArticleStoredData>

    @Query("SELECT * FROM ${Const.TABLENAME_ARTICLE} WHERE publishedAt = :publishedAt")
    fun getArticleByRow(publishedAt: String): ArticleStoredData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: ArticleStoredData)

    @Query("DELETE FROM ${Const.TABLENAME_ARTICLE}")
    fun deleteAllArticle()

    @Query("UPDATE ${Const.TABLENAME_ARTICLE} SET isSelected = :isSelected WHERE publishedAt = :publishedAt")
    fun updateIsSelected(isSelected: Boolean, publishedAt: String)

}