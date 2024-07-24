package com.codingbot.news.core.di;

import com.codingbot.news.domain.datasource.ArticleDataSource;
import com.codingbot.news.domain.repository.ArticleRepositiory;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ArticleViewModelModule_ProvideArticleRepositoryFactory implements Factory<ArticleRepositiory> {
  private final ArticleViewModelModule module;

  private final Provider<ArticleDataSource> articleDataSourceProvider;

  public ArticleViewModelModule_ProvideArticleRepositoryFactory(ArticleViewModelModule module,
      Provider<ArticleDataSource> articleDataSourceProvider) {
    this.module = module;
    this.articleDataSourceProvider = articleDataSourceProvider;
  }

  @Override
  public ArticleRepositiory get() {
    return provideArticleRepository(module, articleDataSourceProvider.get());
  }

  public static ArticleViewModelModule_ProvideArticleRepositoryFactory create(
      ArticleViewModelModule module, Provider<ArticleDataSource> articleDataSourceProvider) {
    return new ArticleViewModelModule_ProvideArticleRepositoryFactory(module, articleDataSourceProvider);
  }

  public static ArticleRepositiory provideArticleRepository(ArticleViewModelModule instance,
      ArticleDataSource articleDataSource) {
    return Preconditions.checkNotNullFromProvides(instance.provideArticleRepository(articleDataSource));
  }
}
