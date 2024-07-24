package com.codingbot.news.core.di

import android.content.Context
import androidx.room.Room
import com.codingbot.news.core.common.ArticleDataBase
import com.codingbot.news.core.common.Const
import com.codingbot.news.domain.ArticleDAO
import com.codingbot.news.domain.repository.ArticleDBRepositiory
import com.codingbot.news.domain.repository.ArticleDBRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ArticleDataBase::class.java,
        Const.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideArticleDao(database: ArticleDataBase) = database.articleDao()


    @Singleton
    @Provides
    fun provideArticleDBRepository(articleDAO: ArticleDAO): ArticleDBRepositiory {
        return ArticleDBRepositoryImpl(articleDAO)
    }
}