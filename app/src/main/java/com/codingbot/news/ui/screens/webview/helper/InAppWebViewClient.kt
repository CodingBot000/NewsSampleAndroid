package com.codingbot.news.ui.screens.webview.helper


import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import timber.log.Timber

class InAppWebViewClient(
    private val setCanGoBack: (Boolean) -> Unit,
    private val setCanGoForward: (Boolean) -> Unit,
    private val shouldOverrideUrlLoading: (Uri) -> Boolean,
    private val onPageStarted: (String) -> Unit,
) : WebViewClient() {


    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        Timber.v("shouldOverrideUrlLoading uri:${request.url}")
        if (shouldOverrideUrlLoading(request.url))
            return true
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Timber.v( "onPageStarted: url:$url" )
        onPageStarted(url)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Timber.v( "onPageFinished: url:$url" )
    }

    override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
        super.doUpdateVisitedHistory(view, url, isReload)
        Timber.v(  "doUpdateVisitedHistory: url:$url" )
        setCanGoBack(view.canGoBack())
        setCanGoForward(view.canGoForward())
    }
}