package com.codingbot.news.domain.repository;

import com.codingbot.news.domain.datasource.ArticleDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class ArticleRepositoryImpl_Factory implements Factory<ArticleRepositoryImpl> {
  private final Provider<ArticleDataSource> articleDataSourceProvider;

  public ArticleRepositoryImpl_Factory(Provider<ArticleDataSource> articleDataSourceProvider) {
    this.articleDataSourceProvider = articleDataSourceProvider;
  }

  @Override
  public ArticleRepositoryImpl get() {
    return newInstance(articleDataSourceProvider.get());
  }

  public static ArticleRepositoryImpl_Factory create(
      Provider<ArticleDataSource> articleDataSourceProvider) {
    return new ArticleRepositoryImpl_Factory(articleDataSourceProvider);
  }

  public static ArticleRepositoryImpl newInstance(ArticleDataSource articleDataSource) {
    return new ArticleRepositoryImpl(articleDataSource);
  }
}
