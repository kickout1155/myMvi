package ru.zenin.delivery.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val interactor: MainInteractor,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            interact = interactor,
            initViewState = MainViewState(
                isLoading = true,
                text = "123"
            )
        ) as T
    }

    class Factory @Inject constructor(
        private val interactor: MainInteractor,
    ) {
        fun create(): MainViewModelFactory {
            return MainViewModelFactory(
                interactor = interactor,
            )
        }
    }
}