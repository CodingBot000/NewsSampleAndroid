package com.codingbot.news.ui.screens.article

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codingbot.news.core.utils.NetworkManager
import com.codingbot.news.core.utils.isScreenWidthOver600dp
import com.codingbot.news.ui.component.AlertDialogBase
import com.codingbot.news.ui.component.ListCell
import com.codingbot.news.ui.component.GridCell
import com.codingbot.news.ui.component.MainHeader
import com.codingbot.news.ui.theme.CustomTheme
import com.codingbot.news.viewmodel.ArticleViewModel

@Composable
fun ArticleScreen(
    onClickCell:(String) -> Unit,
    context: Context = LocalContext.current,
    articleViewModel: ArticleViewModel = hiltViewModel(),

    ) {
    val uiState = articleViewModel.uiState.collectAsStateWithLifecycle()
    val isScreenOver600dp = remember { isScreenWidthOver600dp(context) }

    LaunchedEffect (key1 = Unit) {
        articleViewModel.getArticles(
            q = "book",
            isNetworkAvailable = NetworkManager.checkNetworkState(context),
            dao = null)
    }

    Column(
        modifier = Modifier
            .background(color = CustomTheme.colors.bg)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        MainHeader(
            title = "News",
        )

        if (uiState.value.listData?.size == 0) {
            Text(modifier =
                    Modifier.fillMaxSize(),
                text = "Empty",
                textAlign = TextAlign.Center
                )
            return
        }

        if (isScreenOver600dp) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CustomTheme.colors.bg)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                state = rememberLazyGridState(),
                columns = GridCells.Fixed(3),
                content = {
                    items(uiState.value.listData?.size ?: 0) { index ->
                        val article = uiState.value.listData!![index]
                        GridCell(
                            article = article,
                            onClick = { url ->
                                if (NetworkManager.checkNetworkState(context)) {
                                    onClickCell(url)
                                    articleViewModel.setSelected(index, article)
                                } else {
                                    articleViewModel.showAlert(msg = "Can not access to Network")
                                }
                            },
                            isNetworkAvailable = NetworkManager.checkNetworkState(context)
                        )
                    }
                }
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = CustomTheme.colors.bg
                    )
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
            )
            {
                items(uiState.value.listData?.size ?: 0) { index ->
                    val article = uiState.value.listData!![index]
                    ListCell(
                        article = article,
                        onClickEvent = { url ->
                            if (url.isNotEmpty()) {
                                onClickCell(url)
                                articleViewModel.setSelected(index, article)
                            }
                        },
                        isNetworkAvailable = NetworkManager.checkNetworkState(context))
                }
            }
        }
    }


    if (uiState.value.alertData.second.isNotEmpty()) {
        AlertDialogBase(
            onDismissRequest = { articleViewModel.closeAlertDialog() },
            onConfirmation = {
                articleViewModel.closeAlertDialog()
            },
            dialogTitle = uiState.value.alertData.first,
            dialogText = uiState.value.alertData.second,
        )
    }
}

