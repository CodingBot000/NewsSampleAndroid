package com.codingbot.news.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codingbot.news.core.common.Const

@Entity(tableName = Const.TABLENAME_ARTICLE, primaryKeys = ["publishedAt"])
data class ArticleStoredData(
    @ColumnInfo
    val id_source: String?,
    @ColumnInfo
    val name_source: String,

    @ColumnInfo
    val author: String?,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val description: String?,

    @ColumnInfo
    val url: String,

    @ColumnInfo
    val urlToImage: String?,

    @ColumnInfo
    val publishedAt: String,

    @ColumnInfo
    val content: String?,

    @ColumnInfo
    val isSelected: Boolean = false
)
