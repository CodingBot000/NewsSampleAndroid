package com.codingbot.news.core.utils

import android.content.Context
import com.codingbot.news.domain.model.Article


fun isScreenWidthOver600dp(context: Context): Boolean {
    val displayMetrics = context.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return screenWidthDp >= 600
}

fun makeFileName(article: Article) = "${article.publishedAt}.jpg"

