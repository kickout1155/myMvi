package ru.zenin.delivery.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.zenin.mvicore.*

abstract class BaseMviViewModel<
        VIEW_EVENT : ViewEvent,
        VIEW_STATE : ViewState,
        INTENT : Intent,
        RESULT : Result,
        ACTION : Action,
        INTERATOR : Interact<ACTION, RESULT>,
        >(
    initState: VIEW_STATE,
    private val interactor: INTERATOR,
) : ViewModel() {

    private val _state = MutableStateFlow(initState)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            interactor.result
                .onEach { result ->
                    _state.value = getStateFromResult(_state.value,result)
                }
                .launchIn(viewModelScope)
        }
    }

    fun handleIntent(intent: INTENT) {
        viewModelScope.launch {
            interactor.handleAction(
                getActionFromIntent(intent)
            )
        }
    }

    abstract fun getStateFromResult(lastState: VIEW_STATE, result: RESULT): VIEW_STATE

    protected abstract fun getActionFromIntent(intent: INTENT): ACTION
}
