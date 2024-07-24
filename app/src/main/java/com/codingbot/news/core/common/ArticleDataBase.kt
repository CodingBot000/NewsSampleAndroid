package com.codingbot.news.core.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingbot.news.domain.ArticleDAO
import com.codingbot.news.domain.model.ArticleStoredData

@Database(entities = [ArticleStoredData::class], version = 1)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO
}