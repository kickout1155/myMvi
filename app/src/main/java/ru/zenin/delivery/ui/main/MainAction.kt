package ru.zenin.delivery.ui.main

import ru.zenin.mvicore.Action

sealed class MainAction : Action {
    object ClickIntent : MainAction()
    object ClickEvent : MainAction()
}