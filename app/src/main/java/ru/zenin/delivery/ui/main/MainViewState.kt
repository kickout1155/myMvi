package ru.zenin.delivery.ui.main

import ru.zenin.mvicore.ViewState

data class MainViewState(
    val isLoading: Boolean,
    val text: String,
) : ViewState {
}