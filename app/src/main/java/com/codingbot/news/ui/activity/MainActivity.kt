package com.codingbot.news.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codingbot.news.core.common.Screen
import com.codingbot.news.core.utils.BackKeyDispatcher
import com.codingbot.news.ui.screens.article.ArticleScreen
import com.codingbot.news.ui.screens.webview.WebViewScreen
import com.codingbot.news.ui.theme.NewsSampleTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalRootNavHost =
    staticCompositionLocalOf<NavHostController>{ error("Nav host controller is not provided") }
val LocalBackKeyDispatcher = staticCompositionLocalOf<BackKeyDispatcher> {
    error("you should define 'LocalBackKeyDispatcher provides BackKeyDispatcher'")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsSampleTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ArticleScreen.route
                    ) {
                        composable(route = Screen.ArticleScreen.route) {
                            ArticleScreen(
                                onClickCell = { url ->
                                    startActivity(Intent(this@MainActivity, WebViewActivity::class.java).apply {
                                        putExtra("url", url)
                                    })
                                })
                        }
                    }
                }
            }
        }
    }
}
