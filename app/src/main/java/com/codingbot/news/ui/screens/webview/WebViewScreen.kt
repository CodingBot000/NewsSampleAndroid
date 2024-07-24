package com.codingbot.news.ui.screens.webview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codingbot.news.ui.component.AlertDialogBase


@Composable
fun WebViewScreen(
    url: String,
    onClose:() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (url.isEmpty()) {
            AlertDialogBase(
                onDismissRequest = { onClose() },
                onConfirmation = {
                    onClose()
                },
                dialogTitle = "Error",
                dialogText = "Url is Empty"
            )
            return
        }
        InAppBrowser(
            url = url,
            onClose = {
                onClose()
            })
    }
}