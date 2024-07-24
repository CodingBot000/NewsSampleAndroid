package com.codingbot.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class BaseViewModel<STATE, INTENT>(
    initialState: STATE,
    sharingStarted: SharingStarted = SharingStarted.Lazily
) : ViewModel() {
    private val intentStateMachine = Channel<INTENT> {
        Timber.v("channel unDelievered intent: $it")
    }

    val uiState = intentStateMachine
        .receiveAsFlow()
        .runningFold(initialState) { oldState: STATE, intent: INTENT ->
            oldState.reduce(intent)
        }
        .stateIn(viewModelScope, sharingStarted, initialState)

    protected fun execute(intent: INTENT) {
        viewModelScope.launch {
            intentStateMachine.send(intent)
        }
    }

    protected suspend fun executeInScope(intent: INTENT): Unit = intentStateMachine.send(intent)

    abstract suspend fun STATE.reduce(intent: INTENT): STATE

}
