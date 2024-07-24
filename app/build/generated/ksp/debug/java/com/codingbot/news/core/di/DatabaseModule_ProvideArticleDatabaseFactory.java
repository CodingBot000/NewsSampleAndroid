package com.codingbot.news.core.di;

import android.content.Context;
import com.codingbot.news.core.common.ArticleDataBase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideArticleDatabaseFactory implements Factory<ArticleDataBase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideArticleDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ArticleDataBase get() {
    return provideArticleDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideArticleDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideArticleDatabaseFactory(contextProvider);
  }

  public static ArticleDataBase provideArticleDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideArticleDatabase(context));
  }
}
