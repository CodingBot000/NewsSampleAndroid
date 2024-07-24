package com.codingbot.news.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codingbot.news.core.utils.clickableSingle
import com.codingbot.news.core.utils.dateConvertForDisplay
import com.codingbot.news.core.utils.makeFileName
import com.codingbot.news.domain.model.Article
import com.codingbot.news.ui.theme.CustomTheme


@Composable
fun ListCell(
    article: Article?,
    onClickEvent: (String) -> Unit,
    isNetworkAvailable: Boolean,
)  {

    if (article == null) {
        Text(
            text = "Data Show Error",
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.bodyBold,
        )

        return
    }
    Row(modifier = Modifier.fillMaxWidth())
    {
        NetworkAwareAsyncImage(
            isNetworkAvailable = isNetworkAvailable,
            url = article.urlToImage,
            title = article.title,
            fileName =  makeFileName(article)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .clickableSingle {
                article?.let {
                    onClickEvent(article.url)
                }
            }
        ) {
            Text(
                text = article.title,
                color = if (article.isSelected) {
                    CustomTheme.colors.textColorSelected
                } else {
                    CustomTheme.colors.black
                },
                style = CustomTheme.typography.bodyBold,
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .fillMaxWidth(),
                text = article.author ?: "empty",
                color = CustomTheme.colors.black,
                style = CustomTheme.typography.caption2Bold,
                textAlign = TextAlign.Right
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = dateConvertForDisplay(article.publishedAt),
                color = CustomTheme.colors.black,
                style = CustomTheme.typography.caption2Regular,
                textAlign = TextAlign.Right
            )

            Text(
                text = article.description ?: "empty",
                color = CustomTheme.colors.black,
                style = CustomTheme.typography.caption2Regular,
            )
        }
    }

    Divider(modifier = Modifier.fillMaxWidth()
        .padding(top = 10.dp)
        .background(CustomTheme.colors.divider)
    )

}