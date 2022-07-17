package ru.zenin.delivery.ui.main

import ru.zenin.delivery.ui.base.BaseMviViewModel

class MainViewModel(
    initViewState: MainViewState,
    interact: MainInteractor,
) : BaseMviViewModel<
        MainViewEvent,
        MainViewState,
        MainIntent,
        MainResult,
        MainAction,
        MainInteractor>(
    initViewState, interact
) {

    override fun getActionFromIntent(intent: MainIntent): MainAction {
        return when (intent) {
            MainIntent.ClickIntent -> MainAction.ClickIntent
            MainIntent.ClickEvent -> MainAction.ClickEvent
        }
    }

    override fun getStateFromResult(lastState: MainViewState, result: MainResult): MainViewState {
        return when (result) {
            MainResult.NewIntent -> lastState.copy(text = lastState.text + "1")
            MainResult.NewEvent -> lastState
        }
    }
}