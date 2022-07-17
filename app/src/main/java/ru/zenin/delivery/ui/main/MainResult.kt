package ru.zenin.delivery.ui.main

import ru.zenin.mvicore.Result

sealed class MainResult:Result {
    object NewIntent : MainResult()
    object NewEvent : MainResult()
}