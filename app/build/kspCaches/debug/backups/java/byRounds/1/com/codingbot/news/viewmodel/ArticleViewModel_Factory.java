package com.codingbot.news.viewmodel;

import com.codingbot.news.domain.repository.ArticleDBRepositiory;
import com.codingbot.news.domain.repository.ArticleRepositiory;
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
public final class ArticleViewModel_Factory implements Factory<ArticleViewModel> {
  private final Provider<ArticleRepositiory> articleRepositioryProvider;

  private final Provider<ArticleDBRepositiory> articleDBRepositioryProvider;

  public ArticleViewModel_Factory(Provider<ArticleRepositiory> articleRepositioryProvider,
      Provider<ArticleDBRepositiory> articleDBRepositioryProvider) {
    this.articleRepositioryProvider = articleRepositioryProvider;
    this.articleDBRepositioryProvider = articleDBRepositioryProvider;
  }

  @Override
  public ArticleViewModel get() {
    return newInstance(articleRepositioryProvider.get(), articleDBRepositioryProvider.get());
  }

  public static ArticleViewModel_Factory create(
      Provider<ArticleRepositiory> articleRepositioryProvider,
      Provider<ArticleDBRepositiory> articleDBRepositioryProvider) {
    return new ArticleViewModel_Factory(articleRepositioryProvider, articleDBRepositioryProvider);
  }

  public static ArticleViewModel newInstance(ArticleRepositiory articleRepositiory,
      ArticleDBRepositiory articleDBRepositiory) {
    return new ArticleViewModel(articleRepositiory, articleDBRepositiory);
  }
}
