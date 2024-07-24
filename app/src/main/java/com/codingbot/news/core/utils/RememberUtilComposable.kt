package com.codingbot.news.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun <T> rememberLambda(lambda: T) = remember { lambda }
