package com.codingbot.news.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.codingbot.news.core.utils.clickableSingle
import com.codingbot.news.core.utils.dateConvertForDisplay
import com.codingbot.news.core.utils.makeFileName
import com.codingbot.news.domain.model.Article
import com.codingbot.news.ui.theme.CustomTheme

@Composable
fun GridCell(
    article: Article,
    onClick: (String) -> Unit,
    isNetworkAvailable: Boolean,
) {
    Column(
        modifier = Modifier.clickableSingle {
            onClick(article.url)
        }
    ) {
        NetworkAwareAsyncImageForGrid(
            isNetworkAvailable = isNetworkAvailable,
            url = article.urlToImage,
            title = article.title,
            fileName = makeFileName(article)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = article.title,
            color = if (article.isSelected) {
                CustomTheme.colors.textColorSelected
            } else {
                CustomTheme.colors.black
            },
            style = CustomTheme.typography.bodyBold,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
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
            modifier = Modifier
                .padding(horizontal = 3.dp)
                .fillMaxWidth(),
            text = article.content ?: "content empty",
            color = CustomTheme.colors.black,
            style = CustomTheme.typography.caption2Regular,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.padding(bottom = 10.dp))
    }
}

