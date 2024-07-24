package com.codingbot.news.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.codingbot.news.core.utils.BackKeyDispatcher
import com.codingbot.news.ui.activity.LocalBackKeyDispatcher
import com.codingbot.news.ui.activity.LocalRootNavHost


object CustomTheme {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun NewsSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: CustomTypography = CustomTheme.typography,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val rootNavController = rememberNavController()
    val backKeyDispatcher = BackKeyDispatcher()
    CompositionLocalProvider(
        LocalRootNavHost provides rootNavController,
        LocalTypography provides typography,
        LocalBackKeyDispatcher provides backKeyDispatcher
    ) {
        content()
    }
}