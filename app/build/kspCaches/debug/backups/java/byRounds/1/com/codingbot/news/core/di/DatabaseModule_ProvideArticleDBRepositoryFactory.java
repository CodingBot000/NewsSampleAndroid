package com.codingbot.news.core.di;

import com.codingbot.news.domain.ArticleDAO;
import com.codingbot.news.domain.repository.ArticleDBRepositiory;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideArticleDBRepositoryFactory implements Factory<ArticleDBRepositiory> {
  private final Provider<ArticleDAO> articleDAOProvider;

  public DatabaseModule_ProvideArticleDBRepositoryFactory(Provider<ArticleDAO> articleDAOProvider) {
    this.articleDAOProvider = articleDAOProvider;
  }

  @Override
  public ArticleDBRepositiory get() {
    return provideArticleDBRepository(articleDAOProvider.get());
  }

  public static DatabaseModule_ProvideArticleDBRepositoryFactory create(
      Provider<ArticleDAO> articleDAOProvider) {
    return new DatabaseModule_ProvideArticleDBRepositoryFactory(articleDAOProvider);
  }

  public static ArticleDBRepositiory provideArticleDBRepository(ArticleDAO articleDAO) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideArticleDBRepository(articleDAO));
  }
}
