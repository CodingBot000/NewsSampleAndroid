package com.codingbot.news


import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.codingbot.news.core.common.AppLifecycleObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private val appLifecycleObserver = AppLifecycleObserver()
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
    }
}