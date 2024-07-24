package com.codingbot.news.ui.screens.webview


import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.codingbot.news.R
import com.codingbot.news.core.utils.BackKeyHandler
import com.codingbot.news.core.utils.clickableSingle
import com.codingbot.news.core.utils.rememberLambda
import com.codingbot.news.ui.screens.webview.helper.InAppWebChromeClient
import com.codingbot.news.ui.screens.webview.helper.InAppWebViewClient
import com.codingbot.news.ui.theme.CustomTheme
import timber.log.Timber


/**
 * @param supportSementicColor false 인경우 디폴트는 검정색
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun InAppBrowser(
    url: String = "https://google.com",
    onClose:() -> Unit
) {


    var canClose by remember { mutableStateOf(true) }
    val enableClose by remember { derivedStateOf { canClose } }

    BackKeyHandler {
        if (enableClose)
            onClose()
    }

    Column(modifier = Modifier
//        .background(bgColor)
        .fillMaxSize()
//        .padding(top = appTopPadding)
        .navigationBarsPadding()) {

        val context = LocalContext.current
        val webChromeClient = remember { InAppWebChromeClient() }
        var loadingProgress by remember { mutableStateOf(0) }

        var canRefresh by remember { mutableStateOf(true) }
        val enableRefresh by remember { derivedStateOf { canRefresh } }

        var canGoBack by remember { mutableStateOf(false) }
        val enableGoBack =  remember { derivedStateOf { canGoBack } }

        var canGoForward by remember { mutableStateOf(false) }
        val enableGoForward =  remember { derivedStateOf { canGoForward } }

        val shouldOverrideUrlLoading: (Uri) -> Boolean = rememberLambda { uri ->

            val uriString = uri.toString()

            if (uriString.startsWith("xxxxxx")) {
                Timber.v("특정 url 이 들어오면 제어 하는 코드 삽입")
                true
            } else {
                false
            }
        }

        val webView = remember {
            WebView(context).also { webView ->
                webView.layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT,
                )

                webView.settings.run {
                    javaScriptEnabled = true
                    javaScriptCanOpenWindowsAutomatically = true
                    loadsImagesAutomatically = true
                    domStorageEnabled = true
                    allowFileAccess = true
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    pluginState = WebSettings.PluginState.ON
                    allowFileAccessFromFileURLs = true
                    allowUniversalAccessFromFileURLs = true
                    setSupportZoom(false)
                }

                webView.webViewClient = InAppWebViewClient(
                    setCanGoBack = { canGoBack = it },
                    setCanGoForward = { canGoForward = it },
                    shouldOverrideUrlLoading = shouldOverrideUrlLoading,
                    onPageStarted = {

                    }
                )
                webChromeClient.onLoadingProgress = { loadingProgress = it }

                webView.webChromeClient = webChromeClient
            }
        }

        Box(modifier = Modifier
            .weight(1f)
            .fillMaxSize()
        ) {

            AndroidView(
                modifier = Modifier
                    .fillMaxSize(),
                factory = {
                    webView
                }
            )

            if (loadingProgress in 1..99) {  // 로딩 프로그래스 표시
                val width = LocalConfiguration.current.screenWidthDp / 100f * loadingProgress
                Box(modifier = Modifier
                    .height(2.dp)
                    .width(width.dp)
                )
            }

            LaunchedEffect(url) { webView.loadUrl(url) }

        }


        // 하단 웹뷰 히스토리 제어인데, 요건에 따라 막습니다. 혹시 몰라서 삭제하진 않았습니다.
//        InAppWebViewBottom(
//            enableGoBack = enableGoBack.value,
//            enableGoForward = enableGoForward.value,
//            enableRefresh = enableRefresh,
//            onClickGoBack = webView::goBack,
//            onClickGoForward = webView::goForward,
//            onClickRefresh = webView::reload,
//        )
    }
}

@Composable
private fun InAppWebViewBottom(
    enableGoBack: Boolean = true,
    enableGoForward: Boolean = true,
    enableRefresh: Boolean = true,
    onClickGoBack: () -> Unit = {},
    onClickGoForward: () -> Unit = {},
    onClickRefresh: () -> Unit = {},
) {
    val iconTint = CustomTheme.colors.black
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(34.dp)
    ) {
        val goBackTint = if (enableGoBack)
            iconTint
        else
            iconTint.copy(alpha = 0.2f)
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_left),
            modifier = Modifier
                .size(32.dp)
                .clickableSingle(enableGoBack) { onClickGoBack() },
            contentDescription = null,
            tint = goBackTint,
        )

        Spacer(modifier = Modifier.width(40.dp))

        val goForwardTint = if (enableGoForward)
            iconTint
        else
            iconTint.copy(alpha = 0.2f)
        Icon(
            painter = painterResource(id = R.drawable.icon_arrow_right),
            modifier = Modifier
                .size(32.dp)
                .clickableSingle(enableGoForward) { onClickGoForward() },
            contentDescription = null,
            tint = goForwardTint,
        )

        Spacer(modifier = Modifier.weight(1f))

        val refreshTint = if (enableRefresh)
            iconTint
        else
            iconTint.copy(alpha = 0.2f)

        Icon(
            painter = painterResource(id = R.drawable.icon_refresh_48px),
            modifier = Modifier
                .size(32.dp)
                .clickableSingle(enableRefresh) { onClickRefresh() },
            contentDescription = null,
            tint = refreshTint,
        )
    }
}