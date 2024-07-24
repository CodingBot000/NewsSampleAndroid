package com.codingbot.news.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.codingbot.news.core.utils.articleDataMapper_listDataToStoredData
import com.codingbot.news.core.utils.articleDataMapper_storedDataToListData
import com.codingbot.news.core.utils.articleListgDataMapper_storedDataToListData
import com.codingbot.news.core.utils.deleteImagesInTmpImageByName
import com.codingbot.news.domain.ArticleDAO
import com.codingbot.news.domain.repository.ArticleDBRepositiory
import com.codingbot.news.domain.repository.ArticleRepositiory
import com.codingbot.news.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ArticleUiState(
    val listData: List<Article>? = null,
    val alertData: Pair<String, String> = Pair("", "")
)

sealed interface ArticleIntent {
    data class CellData(val listData: List<Article>): ArticleIntent
    data class ShowAlert(val title: String = "Notice", val msg: String) : ArticleIntent
}

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleRepositiory: ArticleRepositiory,
    private val articleDBRepositiory: ArticleDBRepositiory,
) : BaseViewModel<ArticleUiState, ArticleIntent>(ArticleUiState())
{
    var articlesList = mutableListOf<Article>()
    suspend fun getArticles(q: String, isNetworkAvailable: Boolean, dao: ArticleDAO?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isNetworkAvailable) {
                getArticlesByRemote(q)
            } else {
                getArticlesByLocal()
            }
        }
    }

    private suspend fun getArticlesByRemote(q: String) {
        try {
            val result = articleRepositiory.articles(q = q)
            if (result.isSuccessful) {
                result.body()?.let { response ->
                    updateList(response.articles)
                    val syncList = storeArticleToLocal(response.articles)
                    execute(ArticleIntent.CellData(syncList))
                } ?: run {
                    execute(ArticleIntent.ShowAlert(msg = "Error: body is empty"))
                }

            } else {
                getArticlesByLocal()
                execute(ArticleIntent.ShowAlert(msg = "Error: ${result.code().toString()}"))
            }
        } catch (e: Exception) {
            getArticlesByLocal()
            execute(ArticleIntent.ShowAlert(msg = e.toString()))
        }
    }

    private suspend fun getArticlesByLocal() {
        val articlesLocal = articleDBRepositiory.getArticles()
        val articles = articleListgDataMapper_storedDataToListData(articlesLocal)

        if (articles.isEmpty()) {
            execute(ArticleIntent.ShowAlert(msg = "Cannot Show Data"))
            return
        }
        updateList(articles)
        execute(ArticleIntent.CellData(articles))
    }

    private fun updateList(articles: List<Article>) {
        articlesList.clear()
        articlesList.addAll(articles)
    }

    fun deleteImageFile(context: Context, fileName: String) {
        viewModelScope.launch {
            deleteImagesInTmpImageByName(context, fileName)
        }
    }

    /**
     * 원격으로 가져온 데이터를 처리
     * 1. Room DB에 데이터가 존재하지않으면 local DB에 insert 하고 종료
     * 2. Room DB에 데이터가 있으면 local DB의 해당 데이터의 isSelected 상태를 화면에 보여줄 리스트에  업데이트
     */
    private suspend fun storeArticleToLocal(articles: List<Article>): MutableList<Article> {
        articles.forEach { article ->
            var articleExist = articleDBRepositiory.getArticleByRow(article.publishedAt)
            if (articleExist == null) {
                articleDBRepositiory.insertArticle(
                    articleDataMapper_listDataToStoredData(article)
                )
            } else {
                run {
                    articlesList.forEachIndexed { index, article ->
                        if (article.publishedAt == articleExist.publishedAt
                        ) {
                            articlesList[index] =
                                articleDataMapper_storedDataToListData(articleExist)
                            return@run
                        }
                    }
                }
            }
        }
        return articlesList
    }

    fun setSelected(index: Int, article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            articleDBRepositiory.updateIsSelected(
                isSelected = true,
                publishedAt = article.publishedAt)

            val newList = articlesList.apply {
                val article = get(index)
                set(index, article.copy(isSelected = true))
            }
            var newenwList = mutableListOf<Article>()
            newenwList.addAll(newList)

            execute(ArticleIntent.CellData(newenwList))
        }
    }

    fun showAlert(title: String = "Notice", msg: String) {
        execute(ArticleIntent.ShowAlert(
            title = title,
            msg = msg)
        )
    }

    fun closeAlertDialog() {
        execute(ArticleIntent.ShowAlert(msg = ""))
    }

    override suspend fun ArticleUiState.reduce(intent: ArticleIntent): ArticleUiState =
        when (intent) {
            is ArticleIntent.CellData -> {
                copy(listData = intent.listData.toMutableList())
            }
            is ArticleIntent.ShowAlert -> {
                copy(alertData = Pair(intent.title, intent.msg))
            }
        }

}