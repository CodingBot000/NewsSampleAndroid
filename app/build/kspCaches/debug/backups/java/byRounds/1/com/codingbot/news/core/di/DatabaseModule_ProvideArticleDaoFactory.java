package com.codingbot.news.core.di;

import com.codingbot.news.core.common.ArticleDataBase;
import com.codingbot.news.domain.ArticleDAO;
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
public final class DatabaseModule_ProvideArticleDaoFactory implements Factory<ArticleDAO> {
  private final Provider<ArticleDataBase> databaseProvider;

  public DatabaseModule_ProvideArticleDaoFactory(Provider<ArticleDataBase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ArticleDAO get() {
    return provideArticleDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideArticleDaoFactory create(
      Provider<ArticleDataBase> databaseProvider) {
    return new DatabaseModule_ProvideArticleDaoFactory(databaseProvider);
  }

  public static ArticleDAO provideArticleDao(ArticleDataBase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideArticleDao(database));
  }
}
