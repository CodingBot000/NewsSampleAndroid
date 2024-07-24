package com.codingbot.news.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class CustomColors(
    textColorPrimary: Color,
    bg: Color,
    divider: Color,
    textColorSelected: Color,
    white: Color,
    black: Color,
) {
    var textColorPrimary by mutableStateOf(textColorPrimary)
        private set
    var bg by mutableStateOf(bg)
        private set
    var divider by mutableStateOf(divider)
        private set
    var textColorSelected by mutableStateOf(textColorSelected)
        private set
    var white by mutableStateOf(white)
        private set
    var black by mutableStateOf(black)
        private set
}

fun lightColors() = with(com.codingbot.news.ui.theme.Color) {
    CustomColors(
        textColorPrimary = Black,
        bg = White,
        divider = Gray_20,
        textColorSelected = Red_70,
        white = White,
        black = Black,
    )
}

val LocalColors = staticCompositionLocalOf { lightColors() }
