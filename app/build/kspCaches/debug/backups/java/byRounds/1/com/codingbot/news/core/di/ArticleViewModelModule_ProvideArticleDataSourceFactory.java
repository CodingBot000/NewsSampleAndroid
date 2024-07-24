package com.codingbot.news.core.di;

import com.codingbot.news.domain.datasource.ArticleDataSource;
import com.codingbot.news.domain.service.ApiService;
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
public final class ArticleViewModelModule_ProvideArticleDataSourceFactory implements Factory<ArticleDataSource> {
  private final ArticleViewModelModule module;

  private final Provider<ApiService> apiServiceProvider;

  public ArticleViewModelModule_ProvideArticleDataSourceFactory(ArticleViewModelModule module,
      Provider<ApiService> apiServiceProvider) {
    this.module = module;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public ArticleDataSource get() {
    return provideArticleDataSource(module, apiServiceProvider.get());
  }

  public static ArticleViewModelModule_ProvideArticleDataSourceFactory create(
      ArticleViewModelModule module, Provider<ApiService> apiServiceProvider) {
    return new ArticleViewModelModule_ProvideArticleDataSourceFactory(module, apiServiceProvider);
  }

  public static ArticleDataSource provideArticleDataSource(ArticleViewModelModule instance,
      ApiService apiService) {
    return Preconditions.checkNotNullFromProvides(instance.provideArticleDataSource(apiService));
  }
}
