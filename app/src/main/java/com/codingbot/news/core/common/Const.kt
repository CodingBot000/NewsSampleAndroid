package com.codingbot.news.core.common

object Const {
    var globalMultipleInputTime = 0L

    const val BASE_URL = "https://newsapi.org/"
    const val HTTP_CONNECT_TIMEOUT = 10L
    const val HTTP_READ_TIMEOUT = 20L

    const val API_KEY = "db461cc4a8b546e2921da8b9544a2903"
    const val DATABASE_NAME = "article_db"
    const val TABLENAME_ARTICLE = "ArticleTable"
}

sealed interface Screen {
    val route: String

    object ArticleScreen: Screen {
        override val route: String = ScreenRoutes.ArticleScreen
    }
}

private object ScreenRoutes {
    const val ArticleScreen = "ArticleScreen"
}

