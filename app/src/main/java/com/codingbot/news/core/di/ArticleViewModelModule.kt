package com.codingbot.news.core.di

import com.codingbot.news.domain.datasource.ArticleDataSource
import com.codingbot.news.domain.datasource.ArticleDataSourceImpl
import com.codingbot.news.domain.repository.ArticleRepositiory
import com.codingbot.news.domain.repository.ArticleRepositoryImpl
import com.codingbot.news.domain.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ArticleViewModelModule {

    @Provides
    fun provideArticleRepository(
        articleDataSource: ArticleDataSource
    ): ArticleRepositiory = ArticleRepositoryImpl(articleDataSource)

    @Provides
    fun provideArticleDataSource(
        apiService: ApiService
    ): ArticleDataSource = ArticleDataSourceImpl(apiService)
}