package com.codingbot.news.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.codingbot.news.ui.screens.webview.WebViewScreen
import com.codingbot.news.ui.theme.NewsSampleTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra("url") ?: ""
        setContent {
            NewsSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WebViewScreen(
                        url = url,
                        onClose ={
                            finish()
                        }
                    )
                }
            }
        }
    }
}
