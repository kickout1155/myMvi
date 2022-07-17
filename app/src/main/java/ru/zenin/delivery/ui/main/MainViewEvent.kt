package ru.zenin.delivery.ui.main

import ru.zenin.mvicore.ViewEvent

sealed class MainViewEvent : ViewEvent {
    object SomeEventOne : MainViewEvent()
    object SomeEventTwo : MainViewEvent()
}