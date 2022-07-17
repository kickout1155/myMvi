package ru.zenin.delivery.ui.main

import ru.zenin.mvicore.Intent

sealed class MainIntent : Intent {
    object ClickIntent : MainIntent()
    object ClickEvent : MainIntent()
}