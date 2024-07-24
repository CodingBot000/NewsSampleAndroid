package com.codingbot.news.core.utils


import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import com.codingbot.news.ui.activity.LocalBackKeyDispatcher


@Composable
fun BackKeyHandler(onBackPressed: () -> Unit) {
    val backKeyDispatcher = LocalBackKeyDispatcher.current

    val onBack = rememberLambda(lambda = onBackPressed)
    DisposableEffect(Unit) {
        backKeyDispatcher.add(onBack)
        onDispose {
            backKeyDispatcher.remove(onBack)
        }
    }
}

@Stable
class BackKeyDispatcher {
    val onBackCallbacks = mutableListOf<()->Unit>()

    fun add(onBack: () -> Unit) {
        onBackCallbacks.add(onBack)
    }

    fun remove(onBack: () -> Unit) {
        onBackCallbacks.removeIf { it == onBack }
    }
}

