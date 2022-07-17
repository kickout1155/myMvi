package ru.zenin.mvicore

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class Interact<ACTION : Action, RESULT : Result> {

    private val _results = MutableSharedFlow<RESULT>()
    val result = _results.asSharedFlow()

    protected suspend fun postResult(result: RESULT) {
        _results.emit(result)
    }

    abstract suspend fun handleAction(action: ACTION)

}