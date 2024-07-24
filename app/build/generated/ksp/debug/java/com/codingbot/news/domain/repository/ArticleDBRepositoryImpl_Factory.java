package com.codingbot.news.domain.repository;

import com.codingbot.news.domain.ArticleDAO;
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
public final class ArticleDBRepositoryImpl_Factory implements Factory<ArticleDBRepositoryImpl> {
  private final Provider<ArticleDAO> articleDAOProvider;

  public ArticleDBRepositoryImpl_Factory(Provider<ArticleDAO> articleDAOProvider) {
    this.articleDAOProvider = articleDAOProvider;
  }

  @Override
  public ArticleDBRepositoryImpl get() {
    return newInstance(articleDAOProvider.get());
  }

  public static ArticleDBRepositoryImpl_Factory create(Provider<ArticleDAO> articleDAOProvider) {
    return new ArticleDBRepositoryImpl_Factory(articleDAOProvider);
  }

  public static ArticleDBRepositoryImpl newInstance(ArticleDAO articleDAO) {
    return new ArticleDBRepositoryImpl(articleDAO);
  }
}
