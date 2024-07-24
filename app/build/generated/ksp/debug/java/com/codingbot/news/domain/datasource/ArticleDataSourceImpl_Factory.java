package com.codingbot.news.domain.datasource;

import com.codingbot.news.domain.service.ApiService;
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
public final class ArticleDataSourceImpl_Factory implements Factory<ArticleDataSourceImpl> {
  private final Provider<ApiService> apiServiceProvider;

  public ArticleDataSourceImpl_Factory(Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public ArticleDataSourceImpl get() {
    return newInstance(apiServiceProvider.get());
  }

  public static ArticleDataSourceImpl_Factory create(Provider<ApiService> apiServiceProvider) {
    return new ArticleDataSourceImpl_Factory(apiServiceProvider);
  }

  public static ArticleDataSourceImpl newInstance(ApiService apiService) {
    return new ArticleDataSourceImpl(apiService);
  }
}
