package com.codingbot.news.ui.screens.webview.helper

import android.app.AlertDialog
import android.webkit.ConsoleMessage
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView

class InAppWebChromeClient: WebChromeClient() {
    var onLoadingProgress: ((Int) -> Unit)? = null

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        onLoadingProgress?.invoke(newProgress)
    }

    override fun onJsAlert(
        view: WebView,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        AlertDialog.Builder(view.context)
            .setMessage(message)
            .setPositiveButton("OK") { dlg, _ ->
                dlg.dismiss()
                result?.confirm()
            }
            .setOnCancelListener { dlg ->
                dlg.dismiss()
                result?.confirm()
            }
            .create()
            .show()
        return true
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        return super.onConsoleMessage(consoleMessage)
    }
}