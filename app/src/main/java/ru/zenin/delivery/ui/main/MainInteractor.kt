package ru.zenin.delivery.ui.main

import ru.zenin.mvicore.Interact
import javax.inject.Inject

class MainInteractor @Inject constructor() : Interact<MainAction, MainResult>() {

    override suspend fun handleAction(action: MainAction) {
        when (action) {
            MainAction.ClickIntent -> postResult(MainResult.NewIntent)
            MainAction.ClickEvent -> postResult(MainResult.NewEvent)
        }
    }
}